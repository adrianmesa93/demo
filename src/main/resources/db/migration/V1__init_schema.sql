-- Tabla de Usuarios
CREATE TABLE users (
    id UUID PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL, -- ROLE_USER, ROLE_ADMIN
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Tabla de Planes de Suscripción
CREATE TABLE plans (
    id UUID PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price_cents INT NOT NULL, -- Siempre en céntimos para evitar problemas de redondeo con floats/doubles
    currency VARCHAR(3) NOT NULL DEFAULT 'EUR',
    billing_period VARCHAR(50) NOT NULL, -- MONTHLY, ANNUAL
    active BOOLEAN NOT NULL DEFAULT TRUE
);

-- Tabla de Suscripciones (Relaciona User y Plan)
CREATE TABLE subscriptions (
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL REFERENCES users(id),
    plan_id UUID NOT NULL REFERENCES plans(id),
    status VARCHAR(50) NOT NULL, -- ACTIVE, CANCELED, PAST_DUE
    start_date TIMESTAMP NOT NULL,
    end_date TIMESTAMP NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_plan FOREIGN KEY (plan_id) REFERENCES plans(id)
);