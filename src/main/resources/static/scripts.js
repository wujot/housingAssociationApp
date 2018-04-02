$(document).ready(function() {
    // all custom jQuery will go here

    // change housing association image on hover
    $('#housingAssociation').hover(
        function(){
            $(this).attr('src','houseAssociationBlack.png')
        },
        function(){
            $(this).attr('src','houseAssociationWhite.png')
        }
    );

    // if click image then show/hide nav bar
    $("#housingAssociation").click(function(){
        $("#nav").toggle();
        $("p").toggle();
    });


    // if click alert about occupant deletion
    $("#deleteOccupant").click(function(){
        alert("Occupant was deleted.");
    });

});
