CREATE TABLE IF NOT EXISTS USERS (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    l_name VARCHAR(40),
    f_name VARCHAR(20),
    p_myc VARCHAR(40)
);
CREATE TABLE IF NOT EXISTS ACCOUNTS (
	id INT NOT NULL AUTO_INCREMENT,
	account_number VARCHAR(25) PRIMARY KEY,
	balance DECIMAL,
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES USERS(id)
);
CREATE TABLE IF NOT EXISTS CARDS (
    id INT NOT NULL AUTO_INCREMENT,
    card_number VARCHAR(20) PRIMARY KEY,
    acc_number VARCHAR(25),
    user_id INT,
    FOREIGN KEY (acc_number) REFERENCES accounts(account_number)
)