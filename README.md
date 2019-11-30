# SandS-4
Sorting and Searching Assignment 4

## Backward Search
### Deliverables
⋅⋅* The code that implements the reverse Boyer-Moore search **DONE**

⋅⋅* Describe clearly what you have changed compared to the original Boyer-Moore
implementation and most importantly why. (you are allowed to use the code from
Sedgewick)

⋅⋅* Provide tests that clearly show that your reverse Boyer-Moore implementation is
more efficient (needs less character comparisons) than the original Boyer-Moore
code, which seaeches from left to right. (You might need to find some large bodies of
text that can be used for these tests.)

## Huffman Compression

With this assignment you need to determine the compression-ratio for a Java source file
using the Huffman compression method and compress the text. On the start project which
can be found on the DLO contains all you need to get you started. The class
HuffmanCompression has some methods that you must implement. You are allowed to
add extra methods, please be careful with the access-modifiers. Don’t make every method
public just because you are used to. You are NOT allowed to change the signature of any
of the provided classes! There is also a Node class which you must use when constructing
the Huffman tree. This class also needs some code to work properly.

For calculating the compression-ratio you need to know many occurrences there are for
every unique character in the text and how long (measured in bits) the code is for that
character. Please keep in mind that everything IS a character when compressing text. Not
only the characters form the alphabet, but also slashes, exclamation points, carriage-return,
line-feeds, and horizontal tabs are characters. You don’t have to consider the translation
table itself!
You can assume that 1 character needs 8 bits in the original text!

Once you have determined the number of occurrences for every character you can build the
Huffman-tree. For this you must use the provided Node class.
With the Huffman-tree at hand you should be able to determine the compressed code that
will be used when compressing the file.

Knowing the number of occurrences of each character and the code that will used when
compressing the text you can determine the compression-ratio and compress the text.
One of your tasks is adding extra tests that will ensure that your code works properly.
When nodes have the same weight and both of them are leafs, the you must sort then
alphabetically. If one of them is a node with only a weight and the other is leaf then the leaf
comes first. If neither of them is a leaf the order is undetermined.

### Note
Since Java is not particularly well suited to handle single bits we use Strings instead. So if the
code for a character should be 0110 (e.g. 4 bits) your code can use the String “0110”.

### Report
The report contains a brief description of the written code in plain English1 or Dutch2
(avoid technical mambo-yambo). Also clearly state the encoding of “YES, we made it!” using the
Huffman codes that you derived from the provided file.

And of course you must upload a ZIP-file containing all the Java source files including your
unit tests.
