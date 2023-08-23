-- 코드를 입력하세요
SELECT a.name, a.datetime
from animal_ins a
left join animal_outs b on a.animal_id = b.animal_id
where b.datetime is null
order by a.datetime
limit 3;
