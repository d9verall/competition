$(document).ready(function () {
  'use strict';
  let usernameError = true,
      emailError = true,
      phoneError = true,
      passwordError = true,
      passConfirm = true
  // Detect browser for css purpose
  if (navigator.userAgent.toLowerCase().indexOf('firefox') > -1) {
    $('.form form label').addClass('fontSwitch');
  }

  function tipShow(ele, tip) {
    ele.siblings('.error').text(tip).fadeIn().parent('.form-group').addClass(
        'hasError');
  }

  function tipHidden(ele) {
    ele.siblings('.error').text('').fadeOut().parent('.form-group').removeClass(
        'hasError');
  }

  let $login = $('#login-email');

  function loginValidate(success, fail) {
    if ($login.val().trim().length === 0) {
      return
    }
    $.get(`user/exist?email=${$login.val()}&phone=${$login.val()}`,
        function (res) {
          if (!res) {
            tipShow($login, `用户 ${$login.val()} 不存在`)
            !fail || fail();
          } else {
            tipHidden($login)
            !success || success();
          }
        })
  }

  function registerValidate(ele, success, fail) {
    if (ele.val().trim().length === 0) {
      return
    }
    $.get(`user/exist?email=${ele.val()}&phone=${ele.val()}`,
        function (res) {
          if (res) {
            tipShow(ele, `用户 ${ele.val()} 已存在`)
            !fail || fail();
          } else {
            tipHidden(ele)
            !success || success();
          }
        })
  }

  $login.blur(function () {
    loginValidate()
  })

  // Label effect
  const $input = $('input')
  $input.focus(function () {
    $(this).siblings('label').addClass('active');
  });
  // Form validation
  $input.blur(function () {
    // User Name
    if ($(this).hasClass('name')) {
      if ($(this).val().length === 0) {
        tipShow($(this), '请输入您的用户名')
        usernameError = true;
      } else {
        tipHidden($(this))
        usernameError = false;
      }
    }
    // Email
    if ($(this).hasClass('email')) {
      if ($(this).val().length === 0) {
        tipShow($(this), '请输入你的邮箱地址')
        emailError = true;
      } else {
        registerValidate($(this), function () {
          tipHidden($(this))
          emailError = false;
        }, function () {
          emailError = true;
        })
      }
    }
    // Phone
    if ($(this).hasClass('phone')) {
      if ($(this).val().length !== 11) {
        tipShow($(this), '请输入正确手机号')
        phoneError = true;
      } else {
        registerValidate($(this), function () {
          tipHidden($(this))
          phoneError = false;
        }, function () {
          phoneError = true;
        })
      }
    }
    // PassWord
    if ($(this).hasClass('pass')) {
      if ($(this).val().length < 8) {
        tipShow($(this), '请输入至少八个字符')
        passwordError = true;
      } else {
        tipHidden($(this))
        passwordError = false;
      }
    }
    // PassWord confirmation
    // noinspection JSJQueryEfficiency
    if ($('.pass').val() !== $('.passConfirm').val()) {
      tipShow($('.passConfirm'), '两次输入密码不匹配')
      passConfirm = true;
    } else {
      tipHidden($('.passConfirm'))
      passConfirm = false;
    }
    // label effect
    if ($(this).val().length > 0) {
      $(this).siblings('label').addClass('active');
    } else {
      $(this).siblings('label').removeClass('active');
    }
  });
  // form switch
  $('a.switch').click(function (e) {
    $(this).toggleClass('active');
    e.preventDefault();
    if ($('a.switch').hasClass('active')) {
      $(this).parents('.form-peice').addClass('switched').siblings(
          '.form-peice').removeClass('switched');
    } else {
      $(this).parents('.form-peice').removeClass('switched').siblings(
          '.form-peice').addClass('switched');
    }
  });
  // Form submit
  $('#register').on('click', function () {
    if (usernameError === true || emailError === true || phoneError === true
        || passwordError === true
        || passConfirm === true) {
      $('.name, .email, .pass, .passConfirm, .phone').blur();
    } else {
      $.ajax({
        url: 'register',
        method: 'post',
        headers: {"X-CSRF-TOKEN": $('#csrf').val()},
        contentType: 'application/json',
        data: JSON.stringify({
          name: $('#name').val(),
          email: $('#email').val(),
          phone: $('#phone').val(),
          password: $('#password').val(),
          rePassword: $('#rePassword').val()
        }),
        success: function () {
          $('.forget, .login').addClass('switched');
          setTimeout(function () {
            $('.forget, .login').hide();
          }, 1000);
          let $brand = $('.brand');
          setTimeout(function () {
            $brand.addClass('active');
            $brand.removeClass('col-sm-6');
          }, 1000);
          setTimeout(function () {
            $('.heading').addClass('active');
          }, 1300);
          setTimeout(function () {
            $('.success-msg p').addClass('active');
          }, 1600);
          setTimeout(function () {
            $('.success-msg a').addClass('active');
          }, 1750);
          setTimeout(function () {
            $('.form').fadeOut();
          }, 1000);
        },
        error: function (error) {
          alert(error.responseJSON.message)
        }
      })
    }
  });

  $('#login').on('click', function () {
    loginValidate(function () {
      $('form.login-form').submit();
    })
  })

  $('#register-login').on('click', function () {
    $('.login').removeClass('switched');
    setTimeout(function () {
      $('.forget, .login').show();
    }, 2000);
    setTimeout(function () {
      $brand.addClass('col-sm-6');
    }, 3000)
    let $brand = $('.brand');
    setTimeout(function () {
      $brand.removeClass('active');
      $brand.css('width', '50%');
    }, 1700);
    setTimeout(function () {
      $('.heading').removeClass('active');
    }, 1450);
    setTimeout(function () {
      $('.success-msg p').removeClass('active');
    }, 1150);
    setTimeout(function () {
      $('.success-msg a').removeClass('active');
    }, 1000);
    setTimeout(function () {
      $('.form').fadeIn();
    }, 2000);
  })
});
