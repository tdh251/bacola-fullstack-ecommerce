-- =============================================================================
-- ENUM TYPES
-- =============================================================================
CREATE TYPE user_role AS ENUM ('ROLE_USER', 'ROLE_ADMIN', 'ROLE_STAFF');
CREATE TYPE entity_status AS ENUM ('DRAFT', 'PUBLISHED', 'ARCHIVED');
CREATE TYPE order_status AS ENUM ('PENDING', 'PROCESSING', 'SHIPPED', 'DELIVERED', 'CANCELLED');
CREATE TYPE payment_method AS ENUM ('COD', 'VNPAY', 'MOMO', 'BANK_TRANSFER');
CREATE TYPE payment_status AS ENUM ('PENDING', 'COMPLETED', 'FAILED', 'REFUNDED');

-- =============================================================================
-- Table USERS
-- =============================================================================
CREATE TABLE users (
   id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

   email VARCHAR(150) NOT NULL,
   phone VARCHAR(20),
   full_name VARCHAR(100) NOT NULL,
   avatar_url TEXT,
   role user_role NOT NULL DEFAULT 'ROLE_USER',
   is_verified BOOLEAN NOT NULL DEFAULT FALSE,
   password VARCHAR(255) NOT NULL,

   created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
   updated_at TIMESTAMP WITH TIME ZONE,
   deleted_at TIMESTAMP WITH TIME ZONE,

   CONSTRAINT uq_users_email UNIQUE (email),
   CONSTRAINT uq_users_phone UNIQUE (phone)
);

-- =============================================================================
-- Table PROVINCES
-- =============================================================================
CREATE TABLE provinces (
   id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

   name VARCHAR(100) NOT NULL,
   code VARCHAR(10) NOT NULL,
   division_type VARCHAR(50),
   code_name VARCHAR(100),
   phone_code VARCHAR(50),

   created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
   updated_at TIMESTAMP WITH TIME ZONE,
   deleted_at TIMESTAMP WITH TIME ZONE,

   CONSTRAINT uq_provinces_code UNIQUE (code),
   CONSTRAINT uq_provinces_code_name UNIQUE (code_name)
);

-- =============================================================================
-- Table WARDS
-- =============================================================================
CREATE TABLE wards (
   id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

   name VARCHAR(100) NOT NULL,
   code VARCHAR(10) NOT NULL,
   division_type VARCHAR(50),
   code_name VARCHAR(100),
   province_code VARCHAR(10) NOT NULL,

   created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
   updated_at TIMESTAMP WITH TIME ZONE,
   deleted_at TIMESTAMP WITH TIME ZONE,

   CONSTRAINT uq_wards_code UNIQUE (code),
   CONSTRAINT fk_wards_province FOREIGN KEY (province_code) REFERENCES provinces (code) ON DELETE RESTRICT
);

-- =============================================================================
-- Table ADDRESSES
-- =============================================================================
CREATE TABLE addresses (
   id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

   user_id BIGINT NOT NULL,

   recipient_name VARCHAR(100) NOT NULL,
   recipient_phone VARCHAR(20) NOT NULL,
   email VARCHAR(150),
   address_details TEXT NOT NULL,
   province_id BIGINT NOT NULL,
   ward_id BIGINT NOT NULL,
   latitude NUMERIC(10, 8),
   longitude NUMERIC(11, 8),
   is_primary BOOLEAN NOT NULL DEFAULT FALSE,
   label VARCHAR(50),

   created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
   updated_at TIMESTAMP WITH TIME ZONE,
   deleted_at TIMESTAMP WITH TIME ZONE,

   CONSTRAINT fk_addresses_users FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
   CONSTRAINT fk_address_province FOREIGN KEY (province_id) REFERENCES provinces(id),
   CONSTRAINT fk_address_ward FOREIGN KEY (ward_id) REFERENCES wards(id)
);

-- =============================================================================
-- Table BRANDS
-- =============================================================================
CREATE TABLE brands (
   id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

   name VARCHAR(255) NOT NULL,
   slug VARCHAR(255) NOT NULL UNIQUE,
   logo_url TEXT,
   description VARCHAR(255),
   status entity_status NOT NULL DEFAULT 'DRAFT',

   created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
   updated_at TIMESTAMP WITH TIME ZONE,
   deleted_at TIMESTAMP WITH TIME ZONE
);

-- =============================================================================
-- Table CATEGORIES
-- =============================================================================
CREATE TABLE categories (
   id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

   name VARCHAR(255) NOT NULL,
   slug VARCHAR(255) NOT NULL,
   image_url TEXT,
   description VARCHAR(255),
   sort_order INTEGER DEFAULT 0,
   status entity_status NOT NULL DEFAULT 'DRAFT',
   parent_id BIGINT,

   created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
   updated_at TIMESTAMP WITH TIME ZONE,
   deleted_at TIMESTAMP WITH TIME ZONE,

   CONSTRAINT fk_categories_parent FOREIGN KEY (parent_id) REFERENCES categories (id) ON DELETE SET NULL
);

-- =============================================================================
-- Table ATTRIBUTES
-- =============================================================================
CREATE TABLE attributes (
   id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

   name VARCHAR(100) NOT NULL,
   slug VARCHAR(100) NOT NULL,
   data_type VARCHAR(50),
   parent_id BIGINT,

   created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
   updated_at TIMESTAMP WITH TIME ZONE,
   deleted_at TIMESTAMP WITH TIME ZONE,

   CONSTRAINT uk_attributes_parent_slug UNIQUE (parent_id, slug),
   CONSTRAINT fk_attributes_parent FOREIGN KEY (parent_id) REFERENCES attributes (id) ON DELETE SET NULL
);

-- =============================================================================
-- Table PRODUCTS
-- =============================================================================
CREATE TABLE products (
   id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

   sku VARCHAR(100),
   name VARCHAR(255) NOT NULL,
   brand_id BIGINT,
   slug VARCHAR(255) NOT NULL,
   short_description VARCHAR(500),
   description TEXT,
   price NUMERIC(10, 0) NOT NULL,
   original_price NUMERIC(10, 0),
   cost_price NUMERIC(10, 0),
   stock_quantity INTEGER NOT NULL DEFAULT 0,
   status entity_status NOT NULL DEFAULT 'DRAFT',
   is_featured BOOLEAN NOT NULL DEFAULT FALSE,
   parent_id BIGINT,

   created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
   updated_at TIMESTAMP WITH TIME ZONE,
   deleted_at TIMESTAMP WITH TIME ZONE,

   CONSTRAINT fk_products_parent FOREIGN KEY (parent_id) REFERENCES products (id) ON DELETE RESTRICT,
   CONSTRAINT fk_brand_parent FOREIGN KEY (brand_id) REFERENCES brands (id) ON DELETE RESTRICT
);

-- =============================================================================
-- Table PRODUCT_CATEGORIES
-- =============================================================================
CREATE TABLE product_categories (
   id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

   product_id BIGINT NOT NULL,
   category_id BIGINT NOT NULL,

   created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
   updated_at TIMESTAMP WITH TIME ZONE,
   deleted_at TIMESTAMP WITH TIME ZONE,

   CONSTRAINT uk_product_category UNIQUE (product_id, category_id),
   CONSTRAINT fk_prod_cat_product FOREIGN KEY (product_id) REFERENCES products (id) ON DELETE CASCADE,
   CONSTRAINT fk_prod_cat_category FOREIGN KEY (category_id) REFERENCES categories (id) ON DELETE CASCADE
);

-- =============================================================================
-- Table PRODUCT_ATTRIBUTES
-- =============================================================================
CREATE TABLE product_attributes (
   id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

   product_id BIGINT NOT NULL,
   attribute_id BIGINT NOT NULL,
   custom_value TEXT,

   created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
   updated_at TIMESTAMP WITH TIME ZONE,
   deleted_at TIMESTAMP WITH TIME ZONE,

   CONSTRAINT uk_product_attribute UNIQUE (product_id, attribute_id),
   CONSTRAINT fk_prod_attr_product FOREIGN KEY (product_id) REFERENCES products (id) ON DELETE CASCADE,
   CONSTRAINT fk_prod_attr_attribute FOREIGN KEY (attribute_id) REFERENCES attributes (id) ON DELETE CASCADE
);

-- =============================================================================
-- Tables PRODUCT_IMAGES
-- =============================================================================
CREATE TABLE product_images (
   id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

   product_id BIGINT NOT NULL,
   image_url TEXT NOT NULL,
   alt_text VARCHAR(255) DEFAULT '',
   sort_order INTEGER DEFAULT 0,
   is_primary BOOLEAN NOT NULL DEFAULT FALSE,

   created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
   updated_at TIMESTAMP WITH TIME ZONE,
   deleted_at TIMESTAMP WITH TIME ZONE,

   CONSTRAINT fk_product_images_product FOREIGN KEY (product_id) REFERENCES products (id) ON DELETE CASCADE
);

-- =============================================================================
-- Table CARTS
-- =============================================================================
CREATE TABLE carts (
   id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

   user_id BIGINT NOT NULL,

   created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
   updated_at TIMESTAMP WITH TIME ZONE,
   deleted_at TIMESTAMP WITH TIME ZONE,

   CONSTRAINT uq_carts_user_id UNIQUE (user_id),
   CONSTRAINT fk_carts_users FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

-- =============================================================================
-- Table CART_ITEMS (Sửa fk sang ON DELETE CASCADE)
-- =============================================================================
CREATE TABLE cart_items (
   id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

   cart_id BIGINT NOT NULL,
   product_id BIGINT NOT NULL,
   quantity INTEGER NOT NULL DEFAULT 1,
   unit_price NUMERIC(10, 0) NOT NULL,

   created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
   updated_at TIMESTAMP WITH TIME ZONE,
   deleted_at TIMESTAMP WITH TIME ZONE,

   CONSTRAINT uk_cart_item_product UNIQUE (cart_id, product_id),
   CONSTRAINT chk_cart_item_quantity CHECK (quantity > 0),
   CONSTRAINT fk_cart_items_cart FOREIGN KEY (cart_id) REFERENCES carts (id) ON DELETE CASCADE,
   CONSTRAINT fk_cart_items_product FOREIGN KEY (product_id) REFERENCES products (id) ON DELETE CASCADE
);

-- =============================================================================
-- Table ORDERS 
-- =============================================================================
CREATE TABLE orders (
   id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
   code VARCHAR(50) NOT NULL UNIQUE,

   user_id BIGINT NOT NULL,
   subtotal NUMERIC(12, 0) NOT NULL DEFAULT 0,
   discount_amount NUMERIC(12, 0) NOT NULL DEFAULT 0,
   shipping_fee NUMERIC(12, 0) NOT NULL DEFAULT 0,
   total_amount NUMERIC(12, 0) NOT NULL DEFAULT 0,
   shipping_address JSONB NOT NULL,
   status order_status NOT NULL DEFAULT 'PENDING',
   payment_method payment_method NOT NULL DEFAULT 'COD',
   payment_status payment_status NOT NULL DEFAULT 'PENDING',

   created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
   updated_at TIMESTAMP WITH TIME ZONE,
   deleted_at TIMESTAMP WITH TIME ZONE,

   CONSTRAINT fk_orders_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE RESTRICT
);

-- =============================================================================
-- Table ORDER_ITEMS
-- =============================================================================
CREATE TABLE order_items (
   id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

   order_id BIGINT NOT NULL,
   product_id BIGINT,
   name VARCHAR(255) NOT NULL,
   sku VARCHAR(100),
   unit_price NUMERIC(12, 0) NOT NULL,
   quantity INTEGER NOT NULL DEFAULT 1,
   subtotal NUMERIC(12, 0) NOT NULL,

   created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
   updated_at TIMESTAMP WITH TIME ZONE,
   deleted_at TIMESTAMP WITH TIME ZONE,

   CONSTRAINT chk_order_items_quantity CHECK (quantity > 0),
   CONSTRAINT fk_order_items_order FOREIGN KEY (order_id) REFERENCES orders (id) ON DELETE CASCADE,
   CONSTRAINT fk_order_items_product FOREIGN KEY (product_id) REFERENCES products (id) ON DELETE SET NULL
);

-- =============================================================================
-- Table REFRESH_TOKENS
-- =============================================================================
CREATE TABLE refresh_tokens (
   id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
   user_id BIGINT NOT NULL,
   token VARCHAR(512) NOT NULL UNIQUE,
   expiry_date TIMESTAMPTZ NOT NULL,
   revoked BOOLEAN NOT NULL DEFAULT FALSE,
   created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
   updated_at TIMESTAMP WITH TIME ZONE,
   deleted_at TIMESTAMP WITH TIME ZONE,

   CONSTRAINT fk_refresh_token_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);


-- =============================================================================
-- INDEXES & SOFT-DELETE SAFE UNIQUE INDEXES
-- =============================================================================
CREATE INDEX idx_refresh_tokens_user_id ON refresh_tokens (user_id);
CREATE UNIQUE INDEX uq_categories_active_slug ON categories (slug) WHERE deleted_at IS NULL;


CREATE UNIQUE INDEX uq_products_active_sku ON products (sku) WHERE deleted_at IS NULL;
CREATE UNIQUE INDEX uq_products_active_slug ON products (slug) WHERE deleted_at IS NULL;

CREATE INDEX idx_product_categories_product_id ON product_categories (product_id);
CREATE INDEX idx_product_categories_category_id ON product_categories (category_id);
CREATE INDEX idx_product_attributes_product_id ON product_attributes (product_id);
CREATE INDEX idx_product_attributes_attribute_id ON product_attributes (attribute_id);
CREATE INDEX idx_product_images_product_id ON product_images (product_id);
CREATE INDEX idx_wards_province_code ON wards (province_code);
CREATE INDEX idx_provinces_name ON provinces (name);
CREATE INDEX idx_wards_name ON wards (name);
CREATE INDEX idx_addresses_user_id ON addresses (user_id);
CREATE INDEX idx_cart_items_cart_id ON cart_items (cart_id);
CREATE INDEX idx_cart_items_product_id ON cart_items (product_id);
CREATE INDEX idx_categories_parent_id ON categories (parent_id);
CREATE INDEX idx_attributes_parent_id ON attributes (parent_id);
CREATE INDEX idx_products_parent_id ON products (parent_id);
CREATE INDEX idx_products_status ON products (status);
CREATE INDEX idx_orders_user_id ON orders (user_id);
CREATE INDEX idx_orders_status ON orders (status);
CREATE INDEX idx_orders_created_at ON orders (created_at DESC);
CREATE INDEX idx_order_items_order_id ON order_items (order_id);
CREATE INDEX idx_order_items_product_id ON order_items (product_id);

-- INSERT TABLEs
INSERT INTO users (email, phone, full_name, avatar_url, role, is_verified, password)
VALUES ('admin@gmail.com', 0123456789, 'Admin Đỗ', '', 'ROLE_ADMIN', true, '$2b$10$V9HAi35VDosQU8/gIOFFDeFMASDnmGeMcbr4EB8MdmYwdZ90aGy/G');

INSERT INTO users (email, phone, full_name, avatar_url, role, is_verified, password)
VALUES ('user@gmail.com', 0123456786, 'Trần Đỗ Quốc', '', 'ROLE_USER', true, '$2b$10$V9HAi35VDosQU8/gIOFFDeFMASDnmGeMcbr4EB8MdmYwdZ90aGy/G');

INSERT INTO users (email, phone, full_name, avatar_url, role, is_verified, password)
VALUES ('user2@gmail.com', 0123456784, 'Đỗ Quốc Toàn', '', 'ROLE_USER', true, '$2b$10$V9HAi35VDosQU8/gIOFFDeFMASDnmGeMcbr4EB8MdmYwdZ90aGy/G');