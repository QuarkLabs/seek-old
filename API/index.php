<?php
require ('db_connect.php');

function addUser($conn, $userid, $username, $fullname, $password, $city, $address, $location_lat, $location_lng){
    //send to server
    $sql = "INSERT INTO user VALUES ('$userid','$username','$fullname','$password','$city','$address','$location_lat','$location_lng')";
    if ($conn->query($sql) === TRUE) {
        echo "New record created successfully";
    } else {
        echo "Error: " . $sql . "<br>" . $conn->error;
    }

    $conn->close();
}

function updateUser($conn, $userid, $username, $fullname, $password, $city, $address, $location_lat, $location_lng){
    $sql = "UPDATE user SET username = $username, fullname = $fullname, password = $password, city = $city, address = $address, location_lat = $location_lat, location_lng = $location_lng WHERE userid = $userid";
    if ($conn->query($sql) === TRUE) {
        echo "Record updated successfully";
    } else {
        echo "Error updating record: " . $conn->error;
    }
}
function getUser($conn, $userid){
    $sql = "SELECT * FROM user WHERE user_id = $userid";
    $result = $conn->query($sql);

    //echo $result;
    while($row = $result->fetch_assoc()) {

        $myObj = (Object)[];
        $myObj->username = $row["username"];
        $myObj->fullname = $row["fullname"];
        $myObj->password = $row["password"];
        $myObj->city = $row["city"];
        $myObj->address = $row["address"];
        $myObj->location_lat = $row["location_lat"];
        $myObj->location_lng = $row["location_lng"];
        $myJSON = json_encode($myObj);

        echo $myJSON;
    }
    $conn->close();
}

function search($conn, $userid, $keywords){
    $sql = "SELECT * FROM user where user_id IN (SELECT user_id FROM user_tag INNER JOIN tags ON user_tag.tag_id = tags.tag_id WHERE INSTR('$keywords', tag_name))";
    $result = $conn->query($sql);

    //echo $result;
    while($row = $result->fetch_assoc()) {
        $myObj = (Object)[];
        $myObj->username = $row["username"];
        $myObj->fullname = $row["fullname"];
        $myObj->password = $row["password"];
        $myObj->city = $row["city"];
        $myObj->address = $row["address"];
        $myObj->location_lat = $row["location_lat"];
        $myObj->location_lng = $row["location_lng"];
        $myJSON = json_encode($myObj);

        echo $myJSON;
        echo "~";
    }

    $conn->close();
}


$command = $_POST["command"];
//$command = "SEARCH";

if ($command === "ADD"){
    $userid = $_POST["userid"];
    //$userid = 2;
    $username = $_POST["username"];
    $fullname = $_POST["fullname"];
    $password = $_POST["password"];
    $city = $_POST["city"];
    $address = $_POST["address"];
    $location_lat = $_POST["location_lat"];
    $location_lng = $_POST["location_lng"];
    addUser($conn, $userid, $username, $fullname, $password, $city, $address, $location_lat, $location_lng);
}
else if ($command === "UPDATE"){
    $userid = $_POST["userid"];
    //$userid = 2;
    $username = $_POST["username"];
    $fullname = $_POST["fullname"];
    $password = $_POST["password"];
    $city = $_POST["city"];
    $address = $_POST["address"];
    $location_lat = $_POST["location_lat"];
    $location_lng = $_POST["location_lng"];
    updateUser($conn, $userid, $username, $fullname, $password, $city, $address, $location_lat, $location_lng);
}
else if ($command === "GET"){
    $userid = $_POST["user_id"];
    getUser($conn, $userid);
}
else if ($command === "SEARCH"){
    $keywords = $_POST["keywords"];
    search($conn, $userid, $keywords);
}







?>
