USE designershop;

DROP TABLE IF EXISTS product_image;

CREATE TABLE product_image (
    image_id INT AUTO_INCREMENT PRIMARY KEY,
    image TEXT NOT NULL,
    product_id INT NOT NULL,
    FOREIGN KEY (product_id)
        REFERENCES product (product_id)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8MB4;
