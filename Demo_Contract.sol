pragma solidity ^0.4.7;

contract CeconDemoContract {

    //Wallet Adressen der Prozessteilnehmer
    address private manufacturer = 0xf9dc601481432570ef01a4ce87c06e13dc1fbe11;
    address private supplier = 0x55ef3f5fea1ba9db414a87bb6c4f1ec4176e34bf;
    address private logistics = 0x21ae548a03c2e68fe3129162def065d55895f26e;

    //OrderID der nächsten besetellung
    uint private nextOrderID = 0;
    //Liste der Bezahlungen
    mapping(uint => uint) payments;

    //Kosten einer Holzart in Wei/m³
    uint public pinewoodCosts = 13300000000000000;
    uint public beechwoodCosts = 15000000000000000;
    uint public ashwoodCosts = 21000000000000000;
    uint public oakwoodCosts = 26000000000000000;

    //Neue Bestellung erstellen
    function addNewOrder(int productID, string productName, uint productCount, string orderDetails) onlyManufacturer {
        uint costs = 0;

        //Kosten berechnen
        if(productID == 0)
            costs = pinewoodCosts * productCount;
        else if(productID == 1)
            costs = beechwoodCosts * productCount;
        else if(productID == 2)
            costs = ashwoodCosts * productCount;
        else if(productID == 3)
            costs = oakwoodCosts * productCount;

        emit NewOrder(nextOrderID, productName, productCount, orderDetails, costs);

        //OrderID erhöhen für nächste Bestellung
        nextOrderID++;
    }

    //Bestellung ist in Bearbeitung
    function addPendingOrder(uint orderID) onlySupplier {
        emit PendingOrder(orderID);
    }

    //Lieferdetails werden vom Lieferanten an Logistikunternehmen übermittelt
    function addShippingDetails(uint orderID, uint year, uint month, uint day) onlySupplier {
        emit ShippingDetails(orderID, year, month, day);
    }

    //Versandbestätigung wird an den Hersteller übermittelt
    function addShippingConfirmation(uint orderID) onlyLogistics {
        emit ShippingConfirmation(orderID);
    }

    //Eine Bestellung wird bezahlt
    function addPayment(uint orderID) payable onlyManufacturer {
        //Für die jeweilige orderID wird die Bezahlung aufgenommen
        payments[orderID] = msg.value;

        emit PayedOrder(orderID, msg.value);
    }

    //Bezahlung wird an den Lieferanten ausgeschüttet
    function releasePayment(uint orderID) onlyManufacturer {
        supplier.transfer(payments[orderID]);

        emit PaymentReleased(orderID, payments[orderID]);
    }

    //Modifier

    modifier onlyManufacturer() {
        require(msg.sender == manufacturer);
        _;
    }

    modifier onlySupplier() {
        require(msg.sender == supplier);
        _;
    }

    modifier onlyLogistics() {
        require(msg.sender == logistics);
        _;
    }

    //Events

    event NewOrder(uint orderID, string productName, uint productCount, string orderDetails, uint orderCosts);

    event PendingOrder(uint orderID);

    event ShippingConfirmation(uint orderID);

    event ShippingDetails(uint orderID, uint year, uint month, uint day);

    event PayedOrder(uint orderID, uint orderCosts);

    event PaymentReleased(uint orderID, uint orderCosts);

}