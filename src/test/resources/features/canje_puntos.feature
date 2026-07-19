Feature: Canje de puntos de fidelidad
  Como cliente del programa PuntosMarket
  Quiero canjear mis puntos acumulados
  Para obtener descuentos en mis compras

  Scenario: Canje exitoso de un cliente activo con saldo suficiente
    Given un cliente con DNI "45678912" registrado con 80 puntos y estado activo
    When el cliente canjea 50 puntos
    Then recibe un descuento de 25.0 soles
    And su saldo queda en 30 puntos

  Scenario: Canje rechazado por cliente inactivo
    Given un cliente con DNI "78912345" registrado con 100 puntos y estado inactivo
    When el cliente intenta canjear 50 puntos
    Then el canje es rechazado con el mensaje "El cliente esta inactivo"

  Scenario: Canje rechazado por no alcanzar el minimo de puntos
    Given un cliente con DNI "12345678" registrado con 30 puntos y estado activo
    When el cliente intenta canjear 20 puntos
    Then el canje es rechazado con el mensaje "Se necesita un minimo de 50 puntos para canjear"

  Scenario: Canje rechazado por saldo insuficiente
    Given un cliente con DNI "45678912" registrado con 80 puntos y estado activo
    When el cliente intenta canjear 100 puntos
    Then el canje es rechazado con el mensaje "No puede canjear mas puntos de los que tiene"