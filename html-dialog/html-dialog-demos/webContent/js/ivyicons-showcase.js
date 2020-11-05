var ivyLayoutStylesheet = Object.values(document.styleSheets).filter(sheet => sheet.href?.includes("ivyicons.css"))[0];
var ivyicons = Object.values(ivyLayoutStylesheet.rules).filter(rule => rule.selectorText?.startsWith(".ivyicon-"));
ivyicons.sort((a, b) => (a.selectorText > b.selectorText) ? 1 : -1);

var element = document.getElementById("icons");
element.innerHTML = '';

ivyicons.forEach(icon => {
  var iconClass = icon.selectorText.substring(1, icon.selectorText.length - 8);

  var iconImage = document.createElement("i");
  iconImage.className = "icon " + iconClass;

  var iconTitle = document.createElement("p");
  iconTitle.appendChild(document.createTextNode("icon " + iconClass));
  
  var iconDiv = document.createElement("div");
  iconDiv.className = "p-col icon-block";
  iconDiv.appendChild(iconImage)
  iconDiv.appendChild(iconTitle);
  element.appendChild(iconDiv);
});

function search() {
  var filter = document.querySelector("#search input").value;
  console.log(filter)
  document.querySelectorAll(".icon-block").forEach(i => {
    if (i.querySelector("p").textContent.includes(filter)) {
      i.style.display = "block";
    }
    else {
      i.style.display = "none";
    }
  });
}
