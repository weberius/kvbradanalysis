select count(number) numberof, geom 
from analysis 
group by geom 
order by numberof desc