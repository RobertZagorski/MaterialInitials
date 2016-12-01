# Material Initials

An indicator that can be used as a background of person image or when it is unavailable in every application that utilises human resouces (numbers in contact apps, messages in chat applications, etc...)

## Possibilities:

  1. Initials made from one pair of `String`:
  
       [![One pair](./library/graphics/1pair.png "One pair")](./library/graphics/1pair.png "One pair")
       
  2. Initials made from two pairs of `String`:
  
       [![Two pairs](./library/graphics/2pairs.png "Two pairs")]
       
  3. Initials made from three pairs of `String`:
  
       [![Three pairs](./library/graphics/3pairs.png "Three pairs")]
       
  4. Initials made from four pairs of `String`:
  
       [![Four pairs](./library/graphics/4pairs.png "Four pairs")]
       
  5. Three letter initials:
       
       [![Three letter pairs](./library/graphics/3letter.png "Three letter pairs")]
       
  6. Customisable alpha of letters:
  
       [![Customisable alpha](./library/graphics/alpha.png "Customisable alpha")]
  
  7. Black tinted letters:
    
       [![Black tinted letters](./library/graphics/black.png "Black tinted letters")]
       
  8. Rotated letters:
    
       [![Rotated letters](./library/graphics/rotated.png "Rotated letters")]


## Usage:

Add [`MaterialInitials`](./library/src/main/java/com/materialinitials/MaterialInitials.java) view in your .xml

    <com.rzagorski.materialinitials.MaterialInitials
        android:id="@+id/image"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"/>

or use [`MaterialInitialsDrawable`](./library/src/main/java/com/materialinitials/MaterialInitialsDrawable.java) dynamically in your code:

    MaterialInitialsDrawable drawable = new MaterialInitialsDrawable();
    image.setImageDrawable(drawable);