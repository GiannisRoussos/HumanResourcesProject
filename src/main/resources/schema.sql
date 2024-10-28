/* One to many with employee and project */
CREATE TABLE Department
(
    id              BINARY(16) PRIMARY KEY,
    department_name VARCHAR(255) NOT NULL
);

/* Employee (one to many with Department, many-to-many with Project */
CREATE TABLE Employee
(
    id            BINARY(16) PRIMARY KEY,
    first_name    VARCHAR(255),
    last_name     VARCHAR(255),
    email         VARCHAR(255),
    phone_number  VARCHAR(50),
    hire_date     DATE,
    title         VARCHAR(255),
    description   TEXT,
    personal_data TEXT,
    department_id BINARY(16)
);

/* Project table (one to many with department, many to many with employee) */
CREATE TABLE Project
(
    id              BINARY(16) NOT NULL PRIMARY KEY,
    project_name    VARCHAR(255),
    required_skills VARCHAR(255),
    department_id   BINARY(16),
    CONSTRAINT fk_department FOREIGN KEY (department_id) REFERENCES Department (id)
);


/*EmployeeProject table for many-to-many relationship between Employee and Project*/
CREATE TABLE employee_projects
(
    project_id  BINARY(16),
    employee_id BINARY(16),
    CONSTRAINT fk_project FOREIGN KEY (project_id) REFERENCES Project (id),
    CONSTRAINT fk_employee FOREIGN KEY (employee_id) REFERENCES Employee (id),
    PRIMARY KEY (project_id, employee_id)
);


/*Contract table (one-to-one relationship with Employee) */
CREATE TABLE Contract
(
    id          BINARY(16) NOT NULL PRIMARY KEY,
    employee_id BINARY(16) UNIQUE,
    start_date  DATE,
    end_date    DATE,
    salary      DECIMAL(10, 2),
    CONSTRAINT fk_employee_contract FOREIGN KEY (employee_id) REFERENCES Employee (id)
);

describe Department;

/*Create employeedetails table */
CREATE TABLE Employee_Details
(
    id           BINARY(16) NOT NULL PRIMARY KEY,
    employee_id  BINARY(16) UNIQUE,
    skills       VARCHAR(255),
    availability BOOLEAN,
    is_department_head BOOLEAN,
    CONSTRAINT fk_employee_details_employee FOREIGN KEY (employee_id) REFERENCES Employee (id)
);

CREATE TABLE time_entry
(
    id          BINARY(16) PRIMARY KEY,
    employee_id BINARY(16)     NOT NULL,
    project_id  BINARY(16)     NOT NULL,
    hours       DECIMAL(10, 2) NOT NULL,
    entry_date  DATE           NOT NULL,

    -- Foreign Key Constraints
    CONSTRAINT fk_time_entry_employee FOREIGN KEY (employee_id) REFERENCES Employee (id),
    CONSTRAINT fk_time_entry_project FOREIGN KEY (project_id) REFERENCES Project (id)
);

CREATE TABLE performance_review
(
    id                BINARY(16) NOT NULL PRIMARY KEY,
    employee_id       BINARY(16) NOT NULL,
    review_date       DATE   NOT NULL,
    reviewer          VARCHAR(255),
    comments          VARCHAR(5000),
    rating            INT    NOT NULL,
    adjustment_date   DATE,
    contract_id       BINARY(16) NOT NULL,
    FOREIGN KEY (employee_id) REFERENCES employee (id),
    FOREIGN KEY (contract_id) REFERENCES contract (id)
);