USE designershop;

DROP TABLE IF EXISTS cart_items;

CREATE TABLE cart_items (
    item_id INT AUTO_INCREMENT PRIMARY KEY,
    quantity INT DEFAULT 1 NOT NULL,
    added_date DATETIME NOT NULL,
    product_id INT NOT NULL,
    cart_id INT NOT NULL,
    UNIQUE (product_id , cart_id),
    FOREIGN KEY (cart_id)
        REFERENCES cart (cart_id)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8MB4;
