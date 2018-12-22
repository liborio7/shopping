CREATE TABLE IF NOT EXISTS `item` (
  `id`       VARCHAR(48),
  `name`     VARCHAR(250),
  `category` VARCHAR(250),
  `price`    BIGINT,
  `imported` BOOLEAN,
  PRIMARY KEY (`id`)
);

MERGE INTO `item` VALUES
  ('67870ad6-bc7c-47cb-bcad-96b18b2366ad', 'first book', 'BOOK', 1249, FALSE),
  ('1f8e66f8-07ef-4bb4-bd1d-c8ea7654818a', 'first music CD', 'OTHER', 1499, FALSE),
  ('f1cfa5bd-abcf-4d22-904d-58675f7e1d7c', 'first chocolate bar', 'FOOD', 85, FALSE),
  ('1aaec938-8e02-4379-b338-3b55f1275859', 'second imported box of chocolates', 'FOOD', 1000, TRUE),
  ('39d3f34d-1812-4b62-b2f0-cfcfa993aed0', 'second imported bottle of perfume', 'OTHER', 4750,
   TRUE),
  ('f07c7b95-98d4-4621-8cf9-a6fc1c4ea02d', 'third imported bottle of perfume', 'OTHER', 2799, TRUE),
  ('80ed3311-5b14-43ee-80a0-59305ebda65e', 'third bottle of perfume', 'OTHER', 1899, FALSE),
  ('b23cef17-ea11-4d0f-802c-956324d5ecfc', 'third packet of headache pills', 'MEDICAL', 975, FALSE),
  ('2eb76baa-1add-417b-bd22-c038b2df6347', 'third box of imported chocolates', 'FOOD', 1125, TRUE);
