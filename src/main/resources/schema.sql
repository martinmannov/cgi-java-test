CREATE TABLE loan
(
    id               INT AUTO_INCREMENT PRIMARY KEY,
    created_at       DATE           NOT NULL,
    updated_at       DATE,
    principal_amount DECIMAL(15, 2) NOT NULL,
    interest_rate    DECIMAL(5, 2)  NOT NULL,
    term             INT            NOT NULL,
    start_date       DATE,
    end_date         DATE,
    discount_rate    DECIMAL(5, 2)
);