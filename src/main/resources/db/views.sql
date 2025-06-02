-- Vue pour afficher les problèmes de clés étrangères
CREATE OR REPLACE VIEW vw_foreign_key_issues AS
SELECT 
    TABLE_NAME,
    COLUMN_NAME,
    CONSTRAINT_NAME,
    REFERENCED_TABLE_NAME,
    REFERENCED_COLUMN_NAME
FROM 
    INFORMATION_SCHEMA.KEY_COLUMN_USAGE
WHERE 
    REFERENCED_TABLE_SCHEMA = 'gestionnaire_formation'
    AND REFERENCED_TABLE_NAME IS NOT NULL;

-- Vue pour afficher les relations entre les tables
CREATE OR REPLACE VIEW vw_table_relationships AS
SELECT 
    t1.TABLE_NAME as source_table,
    t1.COLUMN_NAME as source_column,
    t2.TABLE_NAME as target_table,
    t2.COLUMN_NAME as target_column,
    t1.CONSTRAINT_NAME
FROM 
    INFORMATION_SCHEMA.KEY_COLUMN_USAGE t1
    JOIN INFORMATION_SCHEMA.KEY_COLUMN_USAGE t2
    ON t1.CONSTRAINT_NAME = t2.CONSTRAINT_NAME
WHERE 
    t1.TABLE_SCHEMA = 'gestionnaire_formation'
    AND t1.REFERENCED_TABLE_NAME IS NOT NULL
    AND t1.TABLE_NAME != t2.TABLE_NAME; 