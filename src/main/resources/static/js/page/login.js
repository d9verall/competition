$(document).ready(function () {
  'use strict';
  let usernameError = true,
      emailError = true,
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
    $.get(`user/exist/${$login.val()}`, function (res) {
      if (res) {
        tipHidden($login)
        console.log('success')
        !success || success();
      } else {
        tipShow($login, `用户 ${$login.val()} 不存在`)
        console.log('failed')
        !fail || fail();
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
      } else if ($(this).val().length > 1 && $(this).val().length <= 6) {
        tipShow($(this), '请输入至少六个字符')
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
        tipHidden($(this))
        emailError = false;
      }
    }
    // Phone
    if ($(this).hasClass('phone')) {
      if ($(this).val().length !== 11) {
        tipShow($(this), '请输入正确手机号')
        emailError = true;
      } else {
        tipHidden($(this))
        emailError = false;
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
    if (usernameError === true || emailError === true || passwordError === true
        || passConfirm === true) {
      $('.name, .email, .pass, .passConfirm, .phone').blur();
    } else {
      $('.forget, .login').addClass('switched');
      setTimeout(function () {
        $('.forget, .login').hide();
      }, 700);
      setTimeout(function () {
        $('.brand').addClass('active');
      }, 300);
      setTimeout(function () {
        $('.heading').addClass('active');
      }, 600);
      setTimeout(function () {
        $('.success-msg p').addClass('active');
      }, 900);
      setTimeout(function () {
        $('.success-msg a').addClass('active');
      }, 1050);
      setTimeout(function () {
        $('.form').hide();
      }, 700);
    }
  });


  $('#login').on('click', function () {
    loginValidate(function () {
      $('form.login-form').submit();
    })
  })

});
