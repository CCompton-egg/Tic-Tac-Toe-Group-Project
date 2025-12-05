/*
 * autocomplete.cpp
 * 
 * The file where you will implement your autocomplete code for Project 4.
 *
 * INSTRUCTOR NOTE: Do not change any of the existing function signatures in
 * this file, or the testcases will fail. 
 */

#include "autocomplete.h"

/** QUESTION 1: FINDNODE **/

// EFFECTS: Traverses the Tree based on the charactes in the prefix and 
//          returns the TreeNode that we end at. If we cannot find a valid node,
//          we return an empty TreeNode. The index variable keeps track of what 
//          character we're at in prefix.
// 
// PSEUDOCODE:
// algorithm FindNode
//   Input: node, prefix, index
//   Output: TreeNode
//
//   if index == prefix.length():
//     return node
//   if node is empty:
//     return empty TreeNode
//   for each child in node's children:
//     if child's value == prefix[index]:
//       return FindNode(child, prefix, index + 1)
//   return empty TreeNode
//
// COMMENTS:
// 
TreeNode<char> FindNode(TreeNode<char> node, std::string prefix, int index) {
  if (index == prefix.length()){
    return node;
  }
  if (node.IsEmpty()){
    return TreeNode<char>();
  }
  std::vector<TreeNode<char>> children = node.GetChildren();
  for (size_t i = 0; i < children.size(); i++){
    if (children[i].GetValue() == prefix[index]){
      return FindNode(children[i], prefix, index + 1);
    }
  }
  return TreeNode<char>();
}

/** QUESTION 2: COLLECTWORDS **/

// EFFECTS: Collects all the words starting from a given TreeNode. For each word, 
//          prepends the word with the prefix and adds it to the results vector.
// 
// PSEUDOCODE:
// algorithm CollectWords
//   Input: node, prefix, results
//   Output: results
//
//   if node is empty:
//     return results
//   if node's value is '$':
//     results.push_back(prefix);
//   for each child in node's children:
//     CollectWords(child, prefix + child.GetValue(), results)
//   return results

// COMMENTS:
//  
void CollectWords(TreeNode<char> node, std::string prefix, std::vector<std::string> &results) {
  if (node.IsEmpty()){
    return;
  }
  if (node.GetValue() == '$'){
    results.push_back(prefix);
    return;
  }
  std::vector<TreeNode<char>> children = node.GetChildren();
  for (size_t i = 0; i < children.size(); i++){
    CollectWords(children[i], prefix + children[i].GetValue(), results);
  }
}

/** QUESTION 3: GETCANDIDATES **/

// EFFECTS: Returns the list of all possible words that can be made starting with
//          the letters in prefix, based on traversing the tree with the given root.
// 
// PSEUDOCODE:
// algorithm GetCandidates
//   Input: root, prefix
//   Output: results

//   results = []
//   node = FindNode(root, prefix, 0)
//   if node is empty:
//     return results
//   CollectWords(node, prefix, results)
//   return results
//
// COMMENTS:
//  
std::vector<std::string> GetCandidates(TreeNode<char> root, std::string prefix) {
  std::vector<std::string> results;
  TreeNode<char> node = FindNode(root, prefix, 0);
  if (node.IsEmpty()){
    return results;
  }
  CollectWords(node, prefix, results);
  return results;
}
