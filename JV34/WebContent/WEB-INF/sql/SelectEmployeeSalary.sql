--選択した社員の給与明細を出力する【未完成】

SELECT em.employee_master, em.employee_name, sm.salary, sm.base_salary, slt.payroll_id, lm.licence_name, opm.official_position_name
FROM (
(
(
employee_master em
JOIN salary_master sm ON em.employee_master = sm.employee_master
)
JOIN salary_licence_table slt ON sm.payroll_id = slt.payroll_id
)
JOIN licence_master lm ON lm.licence_id = slt.licence_id
)
JOIN official_position_master opm ON opm.official_position_id = em.official_position_id
