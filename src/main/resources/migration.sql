CREATE TABLE IF NOT EXISTS `item` (
  `id`       VARCHAR(48),
  `name`     VARCHAR(250),
  `category` VARCHAR(250),
  `price`    BIGINT,
  `imported` BOOLEAN,
  PRIMARY KEY (`id`)
);

MERGE INTO `item` VALUES
  ('67870ad6-bc7c-47cb-bcad-96b18b2366ad', 'book', 'BOOK', 1249, FALSE),
  ('1f8e66f8-07ef-4bb4-bd1d-c8ea7654818a', 'music CD', 'OTHER', 1499, FALSE),
  ('f1cfa5bd-abcf-4d22-904d-58675f7e1d7c', 'chocolate bar', 'FOOD', 85, FALSE),
  ('1aaec938-8e02-4379-b338-3b55f1275859', 'imported box of chocolates', 'FOOD', 1000, TRUE),
  ('39d3f34d-1812-4b62-b2f0-cfcfa993aed0', 'imported bottle of perfume', 'OTHER', 4750, TRUE),
  ('80ed3311-5b14-43ee-80a0-59305ebda65e', 'bottle of perfume', 'OTHER', 1899, FALSE),
  ('b23cef17-ea11-4d0f-802c-956324d5ecfc', 'packet of headache pills', 'MEDICAL', 975, FALSE);