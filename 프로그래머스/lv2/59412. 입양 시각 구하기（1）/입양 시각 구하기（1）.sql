-- 코드를 입력하세요
SELECT date_format(datetime,'%H') hour, count(*) count
from animal_outs
group by date_format(datetime,'%H')
having hour between '09' and '19'
order by date_format(datetime,'%H') asc;