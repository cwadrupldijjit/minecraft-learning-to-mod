# Changelog:

## July 26, 2023

Initial commit with the following features:
- Working integration with VS Code; no files tracked for this, but I've personally tested the development flow and the debugger and both seem to work well with very few hiccups
- Added items for the following:
  - Pizza slice (including texture and crafting recipe)
  - Tomato sauce
  - Pizza crust
  - Shredded cheese
  - Sharp knife
  - Several other incomplete items
- Added counter block with the following features:
  - Rotation when right-clicking with an empty hand, place an item onto it (provided it's included in the appropriate item groups) (TODO: make any item able to be placed on it but when it's in the appropriate mode, then only do the ones included in this mod)
  - Corresponding block entity complete with inventory
  - Held item persists on closing and opening
  - Custom client-only renderer which displays the held item
- Added several other blocks that aren't set up yet
- Create registration flow for things like the custom item groups, items, blocks, etc.
- Updated Fabric a couple of times during the course of this project (which took me more time to figure out than I'd like to admit)

Current focus:
- Make the counter space a place to assemble a pizza
- Make the crust a buildable item only modifiable on a countertop
