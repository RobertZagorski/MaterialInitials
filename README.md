# Material Initials

An indicator that can be used as a background of person image or when it the is no image.
Possibilities include every application that utilises human resources (numbers in contact apps, messages in chat applications, etc...)

## Possibilities:

  **1.** Initials made from one pair of `String`:
  
   ![One pair](./graphics/1pair.png "One pair")
       
  **2.** Initials made from two pairs of `String`:
  
   ![Two pairs](./graphics/2pairs.png "Two pairs")
       
  **3.** Initials made from three pairs of `String`:
  
   ![Three pairs](./graphics/3pairs.png "Three pairs")
       
  **4.** Initials made from four pairs of `String`:
  
   ![Four pairs](./graphics/4pairs.png "Four pairs")
       
  **5.** Three letter initials:
       
   ![Three letter pairs](./graphics/3letter.png "Three letter pairs")
       
  **6.** Customisable alpha of letters:
  
   ![Customisable alpha](./graphics/alpha.png "Customisable alpha")
  
  **7.** Black tinted letters:
    
   ![Black tinted letters](./graphics/black.png "Black tinted letters")
       
  **8.** Rotated letters:
    
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
    
## Conifguration

There are three possible ways to control each aspect of drawing:

  **1.** Use attributes in `.xml` layout
  
  **2.** Use methods of [`MaterialInitials`](./library/src/main/java/com/rzagorski/materialinitials/MaterialInitials.java) to control view
  
  **3.** Use method of [`MaterialInitialsDrawable`](./library/src/main/java/com/rzagorski/materialinitials/MaterialInitialsDrawable.java) to control `Drawable` directly.
  
### Options:

  **1. Initials**<br>
         Initials are composed of beginning letters of words passed to it. Each `String` must contain at least one word and at most 3 words.
      
   <table>
     <tr>
       <th>
            <b>Layout</b>
       </th>
       <th>
            <b>View</b>
       </th>
       <th>
            <b>Drawable</b>
       </th>
     </tr>
     <tr>
       <td>
            <div>`com.rzagorski.materialinitials.MaterialInitials`</div>
            <div>&emsp;&emsp;`app:mi_texts="@array/string_array"/>`</div>
       </td>
       <td>
            <div>`String[] values;`</div>
            <div>`miView.setTexts(values);`</div>
       </td>
       <td>
            <div>`String[] values`</div>
            <div>`miDrawable.setTexts(values);`</div>
       </td>
     </tr>
   </table>
   
  **2. Background colors**<br>
           Background colors must be the same size as texts size. The first initials will be drawn with first color background. The second initials
           will be drawn with second color background, etc.</br>
           If colors are not set, the background color is taken randomly from the set of 
           <a href="https://material.google.com/style/color.html#color-color-palette">500 material colors</a>
        
  <table>
     <tr>
       <th><b>Layout</b></th>
       <th><b>View</b></th>
       <th><b>Drawable</b></th>
     </tr>
     <tr>
       <td>
          <div>`com.rzagorski.materialinitials.MaterialInitials`</div>
          <div>&emsp;&emsp;`app:mi_background_colors="@array/color_array"/>`</div>
       </td>
       <td>
          <div>`int[] colors;`</div><div>`miView.setBackgroundColors(colors);`</div>
       </td>
       <td>
          <div>`int[] colors;`</div><div>`miDrawable.setBackgroundColors(colors);`</div>
       </td>
     </tr>
  </table>
  
  **3. Text color**<br>
           This parameter specifies the rgb channels of a color used to draw initials. Must be between 0 and 255.
        
  <table>
     <tr>
       <th><b>Layout</b></th>
       <th><b>View</b></th>
       <th><b>Drawable</b></th>
     </tr>
     <tr>
       <td>
          <div>`com.rzagorski.materialinitials.MaterialInitials`</div>
          <div>&emsp;&emsp;`app:mi_text_color="@color/color"/>`</div>
       </td>
       <td>
          <div>`int color;`</div><div>`miView.setTextColor(color);`</div>
       </td>
       <td>
          <div>`int color;`</div><div>`miDrawable.setTextColor(color);`</div>
       </td>
     </tr>
  </table>
  
  **4. Text alpha**<br>
           This parameter specifies the alpha channel of a color used to draw initials. Must be between 0 and 255.
        
  <table>
     <tr>
       <th><b>Layout</b></th>
       <th><b>View</b></th>
       <th><b>Drawable</b></th>
     </tr>
     <tr>
       <td>
          <div>`com.rzagorski.materialinitials.MaterialInitials`</div>
          <div>&emsp;&emsp;`app:mi_textAlpha="@integer/alpha"/>`</div>
       </td>
       <td>
          <div>`int alpha;`</div><div>`miView.setTextAlpha(alpha);`</div>
       </td>
       <td>
          <div>`int alpha;`</div><div>`miDrawable.setTextAlpha(alpha);`</div>
       </td>
     </tr>
  </table>
  
  **5. Text rotation**<br>
           This parameter specifies the rotation of initials in clockwise direction. Must be between 0 and 360 modulo.
        
  <table>
     <tr>
       <th><b>Layout</b></th>
       <th><b>View</b></th>
       <th><b>Drawable</b></th>
     </tr>
     <tr>
       <td>
          <div>`com.rzagorski.materialinitials.MaterialInitials`</div>
          <div>&emsp;&emsp;`app:mi_rotation="@integer/rotation/>"`</div>
       </td>
       <td>
          <div>`int rotation;`</div><div>`miView.setTextRotation(rotation);`</div>
       </td>
       <td>
          <div>`int rotation;`</div><div>`miDrawable.setTextRotation(rotation);`</div>
       </td>
     </tr>
  </table>