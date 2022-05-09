
create table isle (
	id serial primary key,
	name varchar(20) unique not null
);

create table region (
	id serial primary key,
	name varchar(60) unique not null,
	isle varchar(20) not null,
	constraint fk_isle
		foreign key (isle)
			references isle(name)
);

create table city (
	id serial primary key,
	name varchar(60) unique not null,
	isle varchar(20) not null,
	region varchar(60) not null,
	constraint fk_isle
		foreign key (isle)
			references isle(name),
	constraint fk_region
		foreign key (region)
			references region(name)
);

create table market (
	id serial primary key,
	name varchar(60) unique not null,
	generic_url boolean not null,
	max_page_add varchar(60)	
);

create table market_info (
	id serial primary key,
	market varchar(60) unique not null,
	search_container_type varchar(15) not null,
	search_container_class varchar(50) not null,
	product_container_type varchar(15) not null,
	product_container_class varchar(50) not null,
	product_link_container_type varchar(15) not null,
	product_link_container_class varchar(50) not null,
	product_link_element_type varchar(15) not null,
	product_image_container_type varchar(15) not null,
	product_image_container_class varchar(50) not null,
	product_image_element_type varchar(15) not null,
	product_image_element_class varchar(50),
	product_image_element_param varchar(15) not null,
	product_detail_container_type varchar(15) not null,
	product_detail_container_class varchar(50) not null,
	product_name_container_type varchar(15) not null,
	product_name_container_class varchar(50) not null,
	product_name_element_type varchar(15) not null,
	product_price_container_type varchar(15) not null,
	product_price_container_class varchar(50) not null,
	product_price_element_type varchar(15) not null,
	product_price_element_class varchar(50),
	constraint fk_market
		foreign key (market)
			references market(name)
);

create table store (
	id serial primary key,
	market varchar(60) not null,
	name varchar(60) unique not null,
	isle varchar(20) not null,
	region varchar(60) not null,
	city varchar(60) not null,
	store_url varchar(100) not null,
	store_search_url varchar(100) not null,
	constraint fk_market
		foreign key (market)
			references market(name),
	constraint fk_isle
		foreign key (isle)
			references isle(name),
	constraint fk_region
		foreign key (region)
			references region(name),
	constraint fk_city
		foreign key (city)
			references city(name)
);
