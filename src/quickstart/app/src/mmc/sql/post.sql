insert into mmc_request(
	mmc_id,
	id,
	description,
	group_id,
	group_description,
	value_const,
	value_example,
	value_type,
	value_length,
	value_scale,
	va_id1,
	va_params1,
	va_id2,
	va_params2
)
select
	t3.id,
	t1.id,
	t1.description,
	t1.group_id,
	t1.group_description,
	t1.value_const,
	t1.value_example,
	t1.value_type,
	t1.value_length,
	t1.value_scale,
	t1.va_id1,
	t1.va_params1,
	t1.va_id2,
	t1.va_params2
from mmc_request t1,mmc_main t2,mmc_main t3
where t1.mmc_id=t2.id and t3.classific=t2.id;

insert into mmc_response(
	mmc_id,
	id,
	description,
	group_id,
	group_description,
	value_const,
	value_example,
	value_type,
	value_length,
	value_scale
)
select
	t3.id,
	t1.id,
	t1.description,
	t1.group_id,
	t1.group_description,
	t1.value_const,
	t1.value_example,
	t1.value_type,
	t1.value_length,
	t1.value_scale
from mmc_response t1,mmc_main t2,mmc_main t3
where t1.mmc_id=t2.id and t3.classific=t2.id;

delete from mmc_request where mmc_id in(select id from mmc_main where classific='T');
delete from mmc_response where mmc_id in(select id from mmc_main where classific='T');
delete from mmc_main where classific='T';