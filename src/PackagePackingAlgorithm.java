import java.util.*;

import static java.util.Collections.*;

public class PackagePackingAlgorithm {

    private static final int MIN_SIZE_ARTICLES = 1; // min size of articles
    private static final int MAX_SIZE_ARTICLES = 9; // max size of articles
    private static final int NB_ARTICLES = 10; // total number of articles
    private static final int PACKAGE_SIZE = 10; // size of package that contains articles
    private static final char PACKAGE_SEPARATION_CHAR = '/'; // packages separation character

    /**
     * This method is used to initialize the list of articles
     * @return List<Integer>
     */
    private static List<Integer> initArticles(){
        List<Integer> articles = new ArrayList<>();
        for(int i = 0; i < NB_ARTICLES; i++){
            articles.add(new Random().nextInt(MAX_SIZE_ARTICLES - MIN_SIZE_ARTICLES) + MIN_SIZE_ARTICLES);
        }
        return articles;
    }

    /**
     * Sort the list of items in descending order
     * @param articles
     * @return List<Integer>
     */
    private static List<Integer> sortArticles(List<Integer> articles){
        articles.sort(reverseOrder());
        return articles;
    }

    /**
     * Get list of articles in packages separated by '/'
     * @param articles
     * @return List<Object>
     */
    private static List<Object> getPackagePackingList(List<Integer> articles){
        List<Object> packagePackingList = new ArrayList<>();
        sortArticles(articles);
        while (articles.size() > 1) {
            List<Integer> indexListOfArticlesToRemove = new ArrayList();
            int currentPackageSizeRemaining = PACKAGE_SIZE;
            for (int i = 0; i < articles.size(); i++) { // Adding articles in one package
                if (articles.get(i) <= currentPackageSizeRemaining) {
                    packagePackingList.add(articles.get(i));
                    indexListOfArticlesToRemove.add(i);
                    currentPackageSizeRemaining = currentPackageSizeRemaining - articles.get(i);
                }
            }
            packagePackingList.add(PACKAGE_SEPARATION_CHAR); // Adding separation character between packages
            for(int i = indexListOfArticlesToRemove.size() - 1; i >= 0; i--){ // Removing articles that were added to the package
                articles.remove(indexListOfArticlesToRemove.get(i).intValue());
            }
        }

        if(articles.size() == 1){ // if last article remaining
            packagePackingList.add(articles.get(0)); // adding the last article in the package list
        }
        else{ // Where no article remains
            packagePackingList.remove(packagePackingList.size()-1); // remove last '/' character
        }
        return packagePackingList;
    }

    /**
     * Print list
     * @param itemList
     */
    private static void printItemList(List itemList){
        for (Object anItemList : itemList) {
            System.out.print(anItemList);
        }
    }

    /**
     * Main method
     * @param args
     */
    public static void main(String[] args) {
        List<Integer> articles = initArticles();
        System.out.print("Initial list of articles is : ");
        printItemList(articles);
        System.out.print("\n");
        System.out.print("List of articles in packages is : ");
        printItemList(getPackagePackingList(articles));
    }
}