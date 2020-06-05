-- insert  house_rent
Insert into house_rent 
select version,createuser,createtime,updateuser,updatetime,id,card_id,transaction_date,
transaction_desc,balance_currency,balance_money,card_type_id,card_type_name,deleted,demoarea 
from credit c 
where c.consume_id = '2-AO0001/1-A10001';