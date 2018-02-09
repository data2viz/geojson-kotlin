QUnit.test( "a Promise-returning test", function( assert ) {
    assert.expect( 1 );

    return new Promise(function (resolve, reject) {
        setTimeout(function () {
            assert.ok(false);
            resolve("result");
        }, 1500);
    });
});