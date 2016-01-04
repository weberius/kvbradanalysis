insert into analysisresult ( 
  select count(number) numberof, geom 
  from analysis 
  group by geom
)