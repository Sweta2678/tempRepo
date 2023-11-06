select * from sizescalemaster ;
select * from sizescalemaster where uniqueid='2017221';

58005
2017221
select * from sizescalemaster where uniqueid='58005';


select 
select * from sizescalemoa where skuuniqueid='6530830' and STYLEUNIQUEID='5041895';
select * from sizescalemoa where skuuniqueid='3850908' and STYLEUNIQUEID='3116616';
select * from sizescalemoa where skuuniqueid='323912317' and STYLEUNIQUEID='317403638';


select sizescaleid from skumaster where uniqueid='6530830' and STYLEUNIQUEID='5041895';
select sizescaleid from skumaster where uniqueid='3850908' and STYLEUNIQUEID='3116616';
select sizescaleid from skumaster where uniqueid='323912317' and STYLEUNIQUEID='317403638';

update skumaster set sizescaleid='58005' where uniqueid='323912317' and STYLEUNIQUEID='317403638';
select uniqueid,STYLEUNIQUEID,sizescaleid from skumaster where sizescaleid is null;
3850908	3116616	
323912317	317403638	


select * from AttributeStore where attributeName = 'SIZES' and objectName = 'SKU' and objectLevel = 'SKU';
