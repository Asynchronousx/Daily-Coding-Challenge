#include <stdio.h>
#include <stdlib.h>

//defining node and list
struct Node {
    int val;
};

struct List {
    struct Node node;
    struct List* npx;
};

//typedef the created structs
typedef struct Node Node;
typedef struct List List;

//xor function
List* XOR_NEXT(List*, List*);
List* XOR_PREV(List*, List*);

//functions declarations
void add(List**, int);
void print(List*);
Node* get(List**, int);

int main() {

    //root of the list
    List* root = NULL;

    //insert
    add(&root, 1);
    add(&root, 42);
    add(&root, 8);
    add(&root, 14);

    //indexing starts at 0 (0=1, 1=42, 2=8, 3=14 etc..)
    Node* getExistentNode = get(&root, 2); 
    Node* getNonExistentNode = get(&root, 9);

    printf("Value found with function get: %d\n", getExistentNode!=NULL? getExistentNode->val: 0);
    printf("Value found with function get: %d\n", getNonExistentNode!=NULL? getExistentNode->val: 0);

    puts("");

    //print
    print(root);

}

//XOR: XOR (or Exclusive OR) is a special kind of OR that returns true ONLY when
//the two inputs are differents. (ie: 0 xor 0 = 0, 1 xor 1 = 0, but (1 xor 0 / 0 xor 1) = 1).
//We can take advantage of that using xor between pointers, storing an address in a single pointer
//that can contain both prev and next.
//A little example: imagine having the list A -> B -> C. 
//We want to traverse from head to tail: we XOR the previous memory address with the next to have the first
//XOR address, and then we have two option: XOR the result with address of NEXT to get the previous node,
//and XOR the result with address PREV to get the next. Imagine A, B, C having memory address value of 
//A = 5, B = 8, C = 11. In binary, we can express those number as: 0101, 1000 and 1011.
//We're going to follow a simple rule: Next node = (Prev ^ Next) ^ Prev and Prev Node = (Prev ^ Next) ^ Next.
//If we want to find the NEXT of A, we do: (NULL ^ B) ^ NULL (where NULL is PREV, because A is the head).
//that is 0000 XOR 1000 XOR 0000 -> 1000, the address of B. 
//But if we want to find the next of B? with the logic of the last operation, we can do: (A^C)^A.
//This is: (0101 ^ 1011) ^ 0101. Then, 0101 ^ 1011 is 1110 (14), then (1110) ^ 0101 is 1011, the address of C.
//Similar is the finding of the prev: we want to find the prev of B. then (A^C)^C (Prev^Next)^Next.
//This is, (0101 ^ 1011) ^ 1011 -> (1110) ^ 1011 is 0101, the address of A.

//Next xor
List* XOR_NEXT(List* prev, List* next) {
    //Next = (Prev ^ Next) ^ Prev
    return (List*) ((((__UINTPTR_TYPE__)prev) ^ ((__UINTPTR_TYPE__)next)) ^ (__UINTPTR_TYPE__)prev);
}

//Prev xor
List* XOR_PREV(List* prev, List* next) {
    //Prev = (Prev ^ Next) ^ Next
    return (List*) ((((__UINTPTR_TYPE__)prev) ^ ((__UINTPTR_TYPE__)next)) ^ (__UINTPTR_TYPE__)next);
}

//add a node
void add(List** root, int value) {

    //new helper node
    List* new_list_node = (List*)malloc(sizeof(List));

    //case list's empty
    if((*root) == NULL) {
        //creating the node
        new_list_node->node.val = value;
        new_list_node->npx = XOR_NEXT(NULL, NULL);
        //assigning the node
        (*root) = new_list_node;
    } else { //list is not empty
        //defining prev and cur
        List* cur = (*root);
        List* prev = NULL;
        List* tmp = NULL;
        
        //while not found the right spot
        while(cur->npx != NULL) {
            tmp = cur;
            cur = XOR_NEXT(prev, cur->npx);
            prev = tmp;
        }

        //assign the value and xor next
        new_list_node->node.val = value;
        new_list_node->npx = XOR_NEXT(NULL, NULL);
        cur->npx = XOR_NEXT(prev, new_list_node);

    }

}

//retrieve a node
Node* get(List** root, int index) {
    
    //case list's empty
    if((*root) == NULL) {
        return NULL;
    } else { //not empty, go search it
        List* cur = (*root);
        List* prev = NULL;
        List* tmp = NULL;
        int index_searched = 0;


        while(index_searched < index && cur != NULL) {
            tmp = cur;
            cur = XOR_NEXT(prev, cur->npx);
            prev = tmp;
            index_searched++;
        }

        if(index_searched != index) {
            return NULL;
        } else {
            return (&cur->node);
        }
    }

}

//print the list
void print(List* root) {

    List* prev = NULL;
    List* tmp = NULL;
    puts("Start of list.");
    while(root != NULL) {
        printf("Value: %d\n", root->node.val);
        tmp = root;
        root = XOR_NEXT(prev, root->npx);
        prev = tmp;
    } puts("End of list.");

}
