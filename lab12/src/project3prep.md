# Project 3 Prep

**What distinguishes a hallway from a room? How are they similar?**

Answer: A hallway is restricted to a width of 1 (or 2). However, they are both types of rooms, and follow all properties of rooms.

**Can you think of an analogy between the process of 
drawing a knight world and randomly generating a world 
using rooms and hallways?**

Answer: The holes for the knight are like rooms, and the floor tiles are like hallways in the sense that you must traverse the path (floor/hallway) to reach the destination (hole/room).

**If you were to start working on world generation, what kind of method would you think of writing first? 
Think back to the lab and the process used to eventually 
get to the full knight world.**

Answer: The first method would create the basic grid of tiles, using a blank grid of all Tileset.NOTHING. This would mitigate the risk of null errors.

**This question is open-ended. Write some classes 
and methods that might be useful for Project 3. Think 
about what helper methods and classes you used for the lab!**

Answer: We could create a hallway-generating method, as well as a hallway class. Additionally, we need to account for the fact that there will be at least one turning hallway, so we could use a method to check if hallways cross paths.   
