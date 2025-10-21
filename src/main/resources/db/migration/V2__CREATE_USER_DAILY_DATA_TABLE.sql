CREATE TABLE user_daily_data (
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL REFERENCES users(id),
    height NUMERIC(5,2),
    weight NUMERIC(5,2),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT now(),
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT now()
);