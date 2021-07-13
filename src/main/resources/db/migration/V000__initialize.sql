CREATE TABLE IF NOT EXISTS categories (
    id SERIAL PRIMARY KEY,
    name VARCHAR(45) NOT NULL
);

CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(45) NOT NULL,
    email VARCHAR(45) NOT NULL,
    password VARCHAR(45) NOT NULL
);

CREATE TABLE IF NOT EXISTS sales (
    id SERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL,
    date DATE NOT NULL,
    CONSTRAINT fk_user_id
        FOREIGN KEY (user_id)
            REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS images (
    id SERIAL PRIMARY KEY,
    name VARCHAR(45) NOT NULL,
    file_name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS products (
    id SERIAL PRIMARY KEY,
    stock_balance INTEGER NOT NULL,
    image_id INTEGER,
    category_id INTEGER NOT NULL,
    price DECIMAL(5,2) DEFAULT (0.00),
    CONSTRAINT fk_image_id
        FOREIGN KEY (image_id)
            REFERENCES images(id)
);

CREATE TABLE IF NOT EXISTS sales_products (
    id SERIAL PRIMARY KEY,
    product_id INTEGER NOT NULL,
    sales_id INTEGER NOT NULL,
    quantity INTEGER NOT NULL,
    total_price INTEGER NOT NULL,
    CONSTRAINT fk_sales_id
        FOREIGN KEY (sales_id)
            REFERENCES sales(id),
    CONSTRAINT fk_product_id
        FOREIGN KEY (product_id)
            REFERENCES products(id)
);