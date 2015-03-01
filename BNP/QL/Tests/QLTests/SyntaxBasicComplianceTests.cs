﻿using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace Tests.QLTests
{
    [TestClass]
    public class SyntaxBasicComplianceTests : QLTestBase
    {
        [TestMethod]
        public void FormBlock()
        {
            string input = @"form My3rdFormNameHere0987654321 {

                             }
                            ";
            Build(input);
            var unit = Parser.formBlock();

            Assert.IsNull(unit.exception);
            Assert.AreEqual(0, Parser.NumberOfSyntaxErrors);
        }

        [TestMethod]
        public void FormBlockWithCommentedLine()
        {
            string input = @"// This line is a commented line T3hUb4h133tH4x0rZ! @#$%^&*()
                             form seeCommentAbove {
                                // Form's body should go here
                             }
                             ";
            Build(input);
            var unit = Parser.formBlock();

            Assert.IsNull(unit.exception);
            Assert.AreEqual(0, Parser.NumberOfSyntaxErrors);
        }
    }
}