$(document).ready(function() {
$("div#form1").append(
// Creating Form Div and Adding <h2> and <p> Paragraph Tag in it.
$("<h3/>").text("Contact Form"), $("<p/>").text("This is my form. Please fill it out. It's awesome!"), $("<form/>", {
action: 'AggiungiPeriferica',
method: '#'
}).append(
// Create <form> Tag and Appending in HTML Div form1.
$("<input/>", {
type: 'text',
id: 'vname',
name: 'name',
placeholder: 'Your Name'
}), // Creating Input Element With Attribute.
$("<input/>", {
type: 'text',
id: 'vemail',
name: 'email',
placeholder: 'Your Email'
}), $("<textarea/>", {
rows: '5px',
cols: '27px',
type: 'text',
id: 'vmsg',
name: 'msg',
placeholder: 'Message'
}), $("<br/>"), $("<input/>", {
type: 'submit',
id: 'submit',
value: 'Submit'
})))
});