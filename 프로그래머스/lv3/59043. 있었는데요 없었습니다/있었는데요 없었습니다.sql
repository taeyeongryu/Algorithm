-- 코드를 입력하세요
SELECT i.animal_id, i.name
from animal_ins i
left join animal_outs o on i.animal_id = o.animal_id
where i.datetime > o.datetime
order by  i.datetime asc;