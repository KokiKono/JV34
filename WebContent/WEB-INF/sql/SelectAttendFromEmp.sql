/*指定社員IDの当月勤怠情報を取得する。*/
 SELECT
 employee_master.employee_master,
 attendance_table.date,
 attendance_table.attendance_flg,
 employee_master.employee_name
 FROM employee_master
 INNER JOIN attendance_table
 ON employee_master.employee_master=attendance_table.employee_master
 WHERE employee_master.employee_master = EMP_ID
 AND
 employee_master.department_id = DEPT_ID
 AND
 attendance_table.date LIKE DATE
 ORDER BY attendance_table.date ASC
 ;
