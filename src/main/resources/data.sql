insert into category(id,name) values
(1,'Main dish'),
(2,'Chili'),
(3,'Liquor'),
(4,'Cakes'),
(5,'Cake mixes'),
(6,'Microwave'),
(7,'Vegetables');

insert into recipe(id,name,yield) values 
(1,'30 Minute Chili',6),
(2,'Amaretto Cake',1),
(3,'Another Zucchini Dish',6);

insert into recipe_category(recipe_id,category_id) values
(1,1),
(1,2);
insert into recipe_category(recipe_id,category_id) values
(2,3),
(2,4),
(2,5);
insert into recipe_category(recipe_id,category_id) values
(3,6),
(3,7);


insert into recipe_ingredients(recipe_id,qty,unit,item,title) values
(1,'1','pound','Ground chuck or lean ground; beef',null),
(1,'1','can','Kidney beans; (12 oz)',null),
(1,'1','can','Tomato soup; undiluted',null),
(1,'1','teaspoon','Salt',null),
(1,'1','tablespoon','Chili powder; (or to taste)',null);

insert into recipe_ingredients(recipe_id,qty,unit,item,title) values
(2,'1 1/2','cups','Toasted Almonds; chopped',null),
(2,'1','package','Yellow cake mix; no pudding',null),
(2,'1','package','Vanilla instant pudding',null),
(2,'1',null,'Eggs',null),
(2,'1/2','cups','Water',null),
(2,'1/2','cups','Amaretto',null),
(2,'1','teaspoon','Almond extract',null),
(2,'1/2','cups','Sugar','GLAZE'),
(2,'1/4','cups','Water','GLAZE'),
(2,'2','tablespoons','Butter','GLAZE'),
(2,'1/4','cups','Amaretto','GLAZE'),
(2,'1/2','teaspoons','Almond extract','GLAZE');





insert into recipe_ingredients(recipe_id,qty,unit,item,title) values
(3,'1','pound','Zucchini; cubed 1/2',null),
(3,'3','tablespoons','Butter or margarine',null),
(3,'3',null,'Eggs; beaten',null),
(3,null,null,'Jar pimentos; 2 1/2 oz, diced',null),
(3,'1','cup','Cheddar cheese; shredded',null),
(3,'1','can','French fried onion rings 3 oz.',null);


insert into recipe_directions(recipe_id,step_id,step) values
(1,1,'Brown the meat in a little butter and cook until the meat is brown -- about
  10 minutes. Add all other ingredients and let simmer for 30 minutes. Your
  choice of hot sauce may be added to taste.
  
  Recipe by: MasterCook Archives
  
  Posted to recipelu-digest Volume 01 Number 577 by Rodeo46898
  &lt;Rodeo46898@aol.com&gt; on Jan 22, 1998');
  
insert into recipe_directions(recipe_id,step_id,step) values
(2,1,'Sprinkle 1 cup almonds into bottom of a well-greased       and floured 10
  tube pan; set aside. Combine cake mix,     pudding mix, eggs, oil, water,
  amaretto, and almond        extract in a mixing bowl; beat on low speed of
  an  electric mixer til dry ingredients are moistened.          Increase
  speed to medium, and beat 4 minutes. Stir in      remaining 1/2 cup
  almonds. Pour batter into prepared       tube pan. Bake at 325~ for 1 hour
  or til a wooden pick inserted in center comes out clean. Cool cake in pan
    10-15 minutes; remove from pan, and cool completely.       Combine sugar,
  water, and butter in a small saucepan;      bring to a boil. Reduce heat to
  medium and gently boil     4-5 minutes, stirring occasionally, until sugar
  dissolves. Remove from heat, and cool 15 minutes. Stir
  in amaretto and almond extract. Punch holes in top of      cake with a
  wooden pick; slowly spoon glaze on top of cake, allowing glaze to absorb in
  cake.
  Posted to MC-Recipe Digest V1 #263
  
  Date: Sun, 27 Oct 1996 20:04:57 +0000
  
  From: Cheryl Gimenez &lt;clgimenez@earthlink.net&gt;');
  
insert into recipe_directions(recipe_id,step_id,step) values
(3,1,'Place zucchini and butter in a 2 quart casserole. Cover with plastic wrap.
  Microwave at high (100%) until tender crisp, 4 to 5 minutes. Stir in egg,
  pimentos and cheese. Blend well. Cover with plastic wrap. Microwave at
  medium (50%) until eggs are set, 10 toll minutes. Sprinkle onion rings on
  top. Microwave at medium (50%) until onion rings are heated, 2 to 2 1/2
  minutes. Makes 6 servings.
  
  Recipe by: diane@keyway.net
  
  Posted to recipelu-digest Volume 01 Number 217 by ''Diane Geary''
  &lt;diane@keyway.net&gt; on Nov 7, 1997');
