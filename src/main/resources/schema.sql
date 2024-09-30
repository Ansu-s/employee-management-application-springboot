

-- Create the employees table inside emp_db schema
CREATE TABLE employees (
                                  id BIGINT PRIMARY KEY,
                                  name VARCHAR(255) NOT NULL,
                                  age INT NOT NULL,
                                  salary DOUBLE NOT NULL,
                                  address VARCHAR(255) NOT NULL
);
