package com.designershop.security;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import com.designershop.entities.UserProfile;
import com.designershop.exceptions.PasswordExpiredException;
import com.designershop.exceptions.UserException;
import com.designershop.repositories.UserProfileRepository;
import com.designershop.utils.DateTimeFormatUtil;
import com.designershop.utils.JwtUtil;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtAuthService {

	private final HttpSession session;
	private final AuthenticationManager authenticationManager;
	private final UserProfileRepository userProfileRepository;

	public String auth(Map<String, Object> request) throws UserException, PasswordExpiredException {
		String username = (String) request.get("username");
		String password = (String) request.get("password");

		UserProfile userProfile = userProfileRepository.findByLogin(username);
		if (Objects.isNull(userProfile)) {
			throw new UserException("此帳戶不存在，請重新確認");
		}

		LocalDateTime currentDateTime = DateTimeFormatUtil.currentDateTime();
		if (LocalDateTime.now().isAfter(userProfile.getPwdExpireDate().toLocalDateTime())) {
			userProfile.setIsLock("Y");
			userProfile.setLockDate(Timestamp.valueOf(currentDateTime));
			userProfileRepository.save(userProfile);
			throw new PasswordExpiredException("密碼已過期，請修改密碼");
		}

		if (!Objects.isNull(userProfile.getUnlockDate())) {
			if (LocalDateTime.now().isAfter(userProfile.getUnlockDate().toLocalDateTime())) {
				userProfile.setIsLock("N");
				userProfile.setLockDate(null);
				userProfile.setUnlockDate(null);
				userProfileRepository.save(userProfile);
			} else {
				throw new UserException("帳號已被鎖定，直到"
						+ userProfile.getUnlockDate().toLocalDateTime().format(DateTimeFormatUtil.FULL_DATE_DASH_TIME));
			}
		}

		try {
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
					password);
			Authentication authentication = authenticationManager.authenticate(authenticationToken);
			String token = JwtUtil.generateToken(authentication);

			userProfile.setSignOnStatus("Y");
			userProfile.setPwdErrorCount(0);
			userProfile.setSignOnToken(token);
			userProfileRepository.save(userProfile);
			session.setAttribute("userProfile", userProfile);

			return token;
		} catch (AuthenticationException e) {
			int pwdErrorCount = userProfile.getPwdErrorCount();
			pwdErrorCount++;
			userProfile.setPwdErrorCount(pwdErrorCount);
			userProfileRepository.save(userProfile);

			if (pwdErrorCount == 5) {

				userProfile.setPwdErrorCount(0);
				userProfile.setIsLock("Y");
				userProfile.setLockDate(Timestamp.valueOf(currentDateTime));
				userProfile.setUnlockDate(Timestamp.valueOf(currentDateTime.plusMinutes(5)));
				userProfileRepository.save(userProfile);
				throw new UserException("密碼輸入錯誤次數過多，帳號已被鎖定，直到"
						+ currentDateTime.plusMinutes(5).format(DateTimeFormatUtil.FULL_DATE_DASH_TIME));
			}

			throw new BadCredentialsException("Authentication failed", e);
		}
	}
}