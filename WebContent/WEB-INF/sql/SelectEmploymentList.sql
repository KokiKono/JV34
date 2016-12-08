SELECT
 employee_master.employee_master,
 employee_master.employee_name_kana,
 employee_master.employee_name,
 employee_master.birthday,
 employee_master.sex,
 employee_master.postal_code,
 employee_master.address,
 employee_master.tell,
 employee_master.department_id,
 department_master.department_name,
 employee_master.sub_department_id,
 sub_department_master.sub_department_name,
 employee_master.official_position_id,
 official_position_master.official_position_name,
 official_position_master.official_position_allowance,
 official_position_master.rank,
 employee_master.joined_month,
 employee_master.leaving_month
 FROM employee_master
 INNER JOIN department_master
 ON employee_master.department_id=department_master.department_id
 LEFT OUTER JOIN sub_department_master
 ON employee_master.sub_department_id=sub_department_master.sub_department_id
 INNER JOIN official_position_master
 ON official_position_master.official_position_id=employee_master.official_position_id
 WHERE
  employee_master.department_id=DEPARTMENT_ID
 ORDER BY employee_master.employee_name_kana ASC
 ;
