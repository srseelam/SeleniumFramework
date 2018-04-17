package framework;
 
public enum UrlType {
               
                DYNAMIC("/s/%s"),
                THEMATIC("/th/%s"),
                PDP("/p/%s"),
                TBA_PDP("/p/-/A-%s"),
                TBA_QI("/TestQuickInfo/-/A-%s"),
                HOME("%s"),
                HOMEPAGE("%s"),
                SLP("/s?searchTerm=%s"),
                REGISTRY("/webapp/wcs/stores/servlet/RegistryGiftGiverCmd?isPreview=true&status=completePageLink&listId=7TVGkEdxgpREVFcjECFypQ&registryType=WD&cumulativeTime=-1&isAjax=false&noOfPings=&viewName=GiftRegistryMyItemsListView"),
                LIST("/webapp/wcs/stores/servlet/TargetListGiftGiverCmd?isPreview=true&status=completePageLink&listId=0I-o0mVFne2wVTqOTzruHw&registryType=OT&cumulativeTime=-1&isAjax=false&noOfPings="),
                SHOPPING_CART("/webapp/wcs/stores/servlet/checkout_itemadd?addToCart=1&calculateOrder=1&calculationUsage=-1&catEntryId=%s&orderId=.&productId=%s&quantity_0=%s"),
                ADDRESS_CREATE("/webapp/wcs/stores/servlet/AddressAdd?firstName=%s&lastName=%s&address1=%s&city=%s&state=%s&zipCode=%s&country=%s&mode=%s&addressType=%s&phone1=%s&phone1Type=%s&isAddressBook=%s&defaultpayment=%s&isOverlay=N&langId=-1&parentUrl=AddressAddUpdate&storeId=10151&catalogId=10051&URL=%s"),
                ADDRESS_DELETE("/webapp/wcs/stores/servlet/AjaxAddressDelete?addressId=%s&storeId=10151&catalogId=10051&langId=-1&URL=AddressBookFormDetails"),
                LOGIN_URL("/webapp/wcs/stores/servlet/EverestLogin?catalogId=10051&currentUrl=UserHome&langId=-1&reLogonURL=EverestLoginView&rememberMe=true&storeId=10151&logonId=%s&logonPassword=%s"),
                LOGIN_URL_UI("/webapp/wcs/stores/servlet/EverestLoginView?langId=-1&storeId=10151&catalogId=10051&lnk=gnav_myaccount"),
                LOGOFF_URL("/webapp/wcs/stores/servlet/Logoff?langId=-1&storeId=10151&catalogId=10051&cmdName=LogOff&URL=EverestLoginView"),
                SHOPPING_CART_TEST_ORDER("/webapp/wcs/stores/servlet/checkout_itemadd?addToCart=1&calculateOrder=1&calculationUsage=-1&catEntryId=%s&orderId=.&productId=%s&quantity_0=%s&testorder=true"),
                SHOPPING_CART_GIFTCARD("/webapp/wcs/stores/servlet/checkout_itemadd?attr-send-type=%s&attr-sizeSelection=%saddToCart=1&calculateOrder=1&calculationUsage=-1&catEntryId=%s&orderId=.&quantity_0=%s"),
                SHOPPING_CART_GIFTCARD_TEST_ORDER("/webapp/wcs/stores/servlet/checkout_itemadd?attr-send-type=%s&attr-sizeSelection=%saddToCart=1&calculateOrder=1&calculationUsage=-1&catEntryId=%s&orderId=.&quantity_0=%s&testorder=true"),
                PAYMENT_DELETE("/webapp/wcs/stores/servlet/AjaxCardDelete?cardId=%s&storeId=10151&catalogId=10051&langId=-1&service=AjaxCardDelete"),
                ORDERITEM_DELETE("/webapp/wcs/stores/servlet/OrderItemDelete?orderItemId=%s&URL=/webapp/wcs/stores/servlet/OrderItemDisplay"),
                SHOPPING_CART_WITH_STORE_PICKUP_ITEM("/webapp/wcs/stores/servlet/checkout_itemadd?&catEntryId=%s&field1=%s&productId=%s&quantity=%s"),
                SHOPPING_CART_WITH_BTS_ITEM("/webapp/wcs/stores/servlet/checkout_itemadd?partNumber_0=%s&quantity_0=%s&BTS=true&field1_0=%s"),
                TRACK_YOUR_ORDER("/ManageOrder"),
                CATEGORY_PAGE("/c/%s"),
                COMPARISON_PAGE("/ProductComparisonCmd?comp=%s"),
                ITEM_UPDATE("/MiscApp/Link.do?method=updateInventory&partnumber=%s&quantity=10000&cluster=%s_"),
                REGISTRY_WITH_CROSSOVER_ITEM("/webapp/wcs/stores/servlet/GiftRegistrySearchViewCmd?registryType=WD&jsRequest=true&catalogId=10051&status=completePage&cumulativeTime=-1&listId=CXVIsCuyK9WnW6psIbXMhQ&noOfPings=1&langId=-1&registryFirstName=default&searchType=basicSearch&segmentGrpName=-1&storeId=10151&registryLastName=new"),
                MY_ACCOUNT("/webapp/wcs/stores/servlet/UserHome?langId=-1&storeId=10151&catalogId=10051&lnk=gnav_myaccount"),
                GIFT_REGISTRY("/gift-registry/"), MIX_AND_MATCH("/mm/-/N-%s")
                ;
               
                private String template;
 
                private UrlType(String fragment){
                                this.template = fragment;
                               
                }
 
                public String fragment() {
                                return template;
                }
 
                public String create(String... parameters) {
                               
                                return String.format(template, (Object[])parameters);
                }
 
}
