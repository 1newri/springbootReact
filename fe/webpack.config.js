const packageJSON = require('./package.json');
const path = require('path');
const webpack = require('webpack');
// const CleanWebpackPlugin = require('clean-webpack-plugin');

const PATHS = {
  build: path.resolve(__dirname, 'target', 'classes', 'META-INF', 'resources', 'webjars', packageJSON.name, packageJSON.version)
};

module.exports = {
  entry: './app/index.js',
  output: {
    path: PATHS.build,
    filename: 'app-bundle.js',
    publicPath: "/assets/"
  },
  devServer: {
    hot: true,
    historyApiFallback: true,
    inline: true,
    port: 8090,
    publicPath: "/assets/",
    contentBase: './tmp',
    proxy: {
      "**": "http://localhost:8085"
    }
  },
  module: {
    rules: [{
        test: /\.(js|jsx)$/,
        exclude: /(node_modules|bower_components)/,
		use: {
          loader: 'babel-loader',
          options: {
            cacheDirectory: true,
            presets: ["@babel/preset-react","@babel/preset-env"],
			plugins: [["@babel/plugin-proposal-class-properties", { "loose": true }]]
          }
        }
      },
      {
        test: /\.css$/,
        use: [
          'style-loader',
          'css-loader'
        ]
      },
      {
        test: /\.(png|svg|jpg|gif)$/,
        use: [
          'file-loader'
        ]
      },
      {
        test: /\.(woff|woff2|eot|ttf|otf)$/,
        use: [
          'file-loader'
        ]
      }
    ]
  },
  devtool: 'inline-source-map',
  plugins: [
    // new CleanWebpackPlugin([PATHS.build])
    new webpack.HotModuleReplacementPlugin()
  ]
};