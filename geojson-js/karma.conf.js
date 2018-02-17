module.exports = function(config) {
    config.set({
        frameworks: ['mocha', 'commonjs'],
        reporters: ['mocha'],
        files: [
            'build/classes/kotlin/test/*.js',
            'build/node_modules/*.js',
            {pattern: 'build/classes/kotlin/test/*.json', watched: true, served: true, included: false}
        ],      
        colors: true,
        logLevel: config.LOG_INFO,
        browsers: [
            // 'Chrome'
            'ChromeHeadless'
        ],
        singleRun: true,
        // singleRun: false,
        autoWatch: false,
        captureTimeout: 5000,
        preprocessors: {
            '**/*.js': ['commonjs']
        }
    })
};
