/**
 *  Light Switch @version v0.1.4
 */

(function () {

  /**
   * @function darkmode
   * @summary: changes the theme to 'dark mode' and save settings to local stroage.
   * Basically, replaces/toggles every CSS class that has '-light' class with '-dark'
   */
  function darkMode() {
    document.querySelectorAll('.bg-light').forEach((element) => {
      element.className = element.className.replace(/-light/g, '-dark');
    });

    document.body.classList.add('bg-dark');

    if (document.body.classList.contains('text-dark')) {
      document.body.classList.replace('text-dark', 'text-light');
    } else {
      document.body.classList.add('text-light');
    }

    // Tables
    let tables = document.querySelectorAll('table');
    for (let i = 0; i < tables.length; i++) {
      // add table-dark class to each table
      tables[i].classList.add('table-dark');
    }

  }

  /**
   * @function lightmode
   * @summary: changes the theme to 'light mode' and save settings to local stroage.
   */
  function lightMode() {
    document.querySelectorAll('.bg-dark').forEach((element) => {
      element.className = element.className.replace(/-dark/g, '-light');
    });

    document.body.classList.add('bg-light');

    if (document.body.classList.contains('text-light')) {
      document.body.classList.replace('text-light', 'text-dark');
    } else {
      document.body.classList.add('text-dark');
    }

    // Tables
    let tables = document.querySelectorAll('table');
    for (let i = 0; i < tables.length; i++) {
      if (tables[i].classList.contains('table-dark')) {
        tables[i].classList.remove('table-dark');
      }
    }

  }

  /**
   * @function onToggleMode
   * @summary: the event handler attached to the switch. calling @darkMode or @lightMode depending on the checked state.
   */
  function onToggleMode() {
    if (lightSwitch) {
      darkMode();
    } else {
      lightMode();
    }
  }

  /**
   * @function getSystemDefaultTheme
   * @summary: get system default theme by media query
   */
  function getSystemDefaultTheme() {
    const darkThemeMq = window.matchMedia('(prefers-color-scheme: dark)');
    if (darkThemeMq.matches) {
      return 'dark';
    }
    return 'light';
  }

  function setup() {

    $.ajax({
      type: "get",
      url: "/prefs/list",
      dataType: 'json',
      success: function (data) {
        if (data.darkMode) {
          darkMode();
        } else {
          lightMode();
        }
      },
      error: function (e) {
        console.log(e);
      }
    });

  }

  setup();
})();
