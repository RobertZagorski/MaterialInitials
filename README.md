# Material Initials

An indicator that can be used as a background of person image or when it the is no image.
Possibilities include every application that utilises human resources (numbers in contact apps, messages in chat applications, etc...)

## Possibilities:

  1. Initials made from one pair of `String`:
  
       ![One pair](./graphics/1pair.png "One pair")
       
  2. Initials made from two pairs of `String`:
  
       ![Two pairs](./graphics/2pairs.png "Two pairs")
       
  3. Initials made from three pairs of `String`:
  
       ![Three pairs](./graphics/3pairs.png "Three pairs")
       
  4. Initials made from four pairs of `String`:
  
       ![Four pairs](./graphics/4pairs.png "Four pairs")
       
  5. Three letter initials:
       
       ![Three letter pairs](./graphics/3letter.png "Three letter pairs")
       
  6. Customisable alpha of letters:
  
       ![Customisable alpha](./graphics/alpha.png "Customisable alpha")
  
  7. Black tinted letters:
    
       ![Black tinted letters](./graphics/black.png "Black tinted letters")
       
  8. Rotated letters:
    
       ![Rotated letters](./graphics/rotated.png "Rotated letters")

## Usage:

Add [`MaterialInitials`](./library/src/main/java/com/rzagorski/materialinitials/MaterialInitials.java) view in your .xml

    <com.rzagorski.materialinitials.MaterialInitials
        android:id="@+id/image"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"/>

or use [`MaterialInitialsDrawable`](./library/src/main/java/com/rzagorski/materialinitials/MaterialInitialsDrawable.java) dynamically in your code:

    MaterialInitialsDrawable drawable = new MaterialInitialsDrawable();
    image.setImageDrawable(drawable);