SELECT ip, COUNT(*) AS NUMBER_OF_REQUESTS
FROM logentry
where date >= '2017-01-01 15:00:00' AND date < '2017-01-01 16:00:00'
GROUP BY ip
HAVING COUNT(*) >= 200;


SELECT request
from logentry where ip = '192.168.11.231';