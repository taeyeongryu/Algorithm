-- 코드를 입력하세요
SELECT mcdp_cd, count(*) as count
from appointment
where date_format (apnt_ymd,'%y%m') = '2205'
group by mcdp_cd
order by count ,mcdp_cd; 