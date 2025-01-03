CREATE TABLE IF NOT EXISTS games (
    id BIGINT PRIMARY KEY AUTOINCREMENT,
    name TEXT not null,
    steam_id TEXT,
    target_price numeric (6, 2), -- MAX: 9999,99
    target_discount numeric (5, 2) -- NAX: 999,99
);