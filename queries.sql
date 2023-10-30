-- Task: 1
SELECT model, speed, hd 
FROM PC 
WHERE price < 500;

-- Task: 2
SELECT DISTINCT maker 
FROM Product 
WHERE type = 'Printer';

-- Task: 3
SELECT model, ram, screen 
FROM Laptop 
WHERE price > 1000;

-- Task: 4
SELECT * 
FROM Printer 
WHERE color = 'y';

-- Task: 5
SELECT model, speed, hd 
FROM PC 
WHERE (cd = '12x' OR cd = '24x') AND price < 600;

-- Task: 6
SELECT maker, speed 
FROM Product, Laptop 
WHERE Product.model = Laptop.model AND hd >= 100;

-- Task: 7
SELECT Laptop.model, Laptop.price 
FROM Laptop 
WHERE Product.model = Laptop.model AND Product.maker = 'B'
UNION
SELECT PC.model, PC.price 
FROM PC 
WHERE Product.model = PC.model AND Product.maker = 'B'
UNION 
SELECT Printer.model, Printer.price 
FROM Printer 
WHERE Product.model = Printer.model AND Product.maker = 'B';

-- Task: 8
SELECT DISTINCT maker 
FROM Product 
WHERE type = 'PC' AND maker NOT IN (SELECT maker FROM Product WHERE type = 'Laptop');

-- Task: 9
SELECT DISTINCT maker 
FROM Product, PC 
WHERE Product.model = PC.model AND PC.speed >= 450;

-- Task: 10
SELECT model, price 
FROM Printer 
WHERE price = (SELECT max(price) FROM Printer);

-- Task: 11
SELECT AVG(speed) 
FROM PC;

-- Task: 12
SELECT AVG(speed) 
FROM Laptop 
WHERE price > 1000;

-- Task: 13
SELECT AVG(speed) 
FROM PC, Product 
WHERE PC.model = Product.model AND maker = 'A';

-- Task: 14
SELECT speed, AVG(price) 
FROM PC 
GROUP BY speed;

-- Task: 15
SELECT hd 
FROM PC 
GROUP BY hd 
HAVING COUNT(hd) > 2;

-- Task: 16
SELECT DISTINCT A.model, B.model, A.speed, A.ram 
FROM PC AS A, PC AS B 
WHERE A.speed=B.speed AND A.ram=B.ram AND B.model < A.model;

-- Task: 17
SELECT DISTINCT type, Laptop.model, Laptop.speed 
FROM Product, Laptop 
WHERE type = 'Laptop' AND speed < (SELECT MIN(speed) FROM PC);

-- Task: 18
SELECT DISTINCT maker, price 
FROM Printer, Product 
WHERE Printer.model = Product.model AND price = (SELECT MIN(price) FROM Printer WHERE color = 'y') AND color = 'y';

-- Task: 19
SELECT maker, AVG(screen) 
FROM Laptop, Product 
WHERE Laptop.model = Product.model 
GROUP BY maker;

-- Task: 20
SELECT maker, COUNT(model) 
FROM PC, Product 
WHERE PC.model = Product.model AND type = 'PC'
GROUP BY maker 
HAVING COUNT(model) >= 3;

-- Task: 21
SELECT maker, MAX(price) 
FROM PC, Product 
WHERE Product.model = PC.model 
GROUP BY maker;

-- Task: 22
SELECT speed, AVG(price) 
FROM PC 
WHERE speed > 600 
GROUP BY speed;

-- Task: 23
SELECT DISTINCT maker 
FROM PC, Product 
WHERE PC.model = Product.model AND PC.speed >= 750 AND maker IN (SELECT maker FROM Laptop, Product WHERE Laptop.model = Product.model AND laptop.speed >= 750);

-- Task: 24
SELECT DISTINCT code 
FROM Laptop 
WHERE price = (SELECT MAX(price) FROM Laptop)
UNION
SELECT code 
FROM PC 
WHERE price = (SELECT MAX(price) FROM PC)
UNION
SELECT code 
FROM Printer 
WHERE price = (SELECT MAX(price) FROM Printer);

-- Task: 25
SELECT DISTINCT maker 
FROM Product WHERE type = 'Printer' AND maker IN
	(SELECT maker 
	FROM Product, PC 
    WHERE Product.model = PC.model AND type = 'PC' AND ram = (SELECT MIN(ram) FROM PC) AND PC.speed = (SELECT MAX(speed) FROM PC));