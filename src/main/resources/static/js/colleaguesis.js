/**
 * Created by tofiques on 3/24/17.
 */
$(document).ready(function() {
    $('input:radio[name=authentication_role]').change(function() {
        if (this.value == 'Yes') {
            $("#authloginin").removeClass("hidden")
        }
        else if (this.value == 'No') {
            $("#authloginin").addClass("hidden")
        }
    });
});