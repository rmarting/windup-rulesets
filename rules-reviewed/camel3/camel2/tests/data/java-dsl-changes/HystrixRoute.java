import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.CompositeRegistry;
import org.apache.camel.impl.PropertyPlaceholderDelegateRegistry;
import org.apache.camel.util.component.*;
import org.apache.camel.processor.loadbalancer.SimpleLoadBalancerSupport;

import org.apache.camel.impl.FileWatcherReloadStrategy;
import org.apache.camel.impl.CamelPostProcessorHelper;
import org.apache.camel.impl.TypedProcessorFactory;
import org.apache.camel.impl.WebSpherePackageScanClassResolver;

import org.apache.camel.support.ReloadStrategySupport;
import org.apache.camel.management.JmxSystemPropertyKeys;

import org.apache.camel.language.Bean;
import org.apache.camel.language.Simple;
import org.apache.camel.language.SpEL;

import org.apache.camel.InvokeOnHeader;
import org.apache.camel.Constant;
import org.apache.camel.InvokeOnHeaders;

import org.apache.camel.builder.xml.XPathBuilder;
import org.apache.camel.language.XPath;
import org.apache.camel.builder.xml.InvalidXPathExpression;

import org.apache.camel.processor.validation.PredicateValidationException;
import org.apache.camel.util.toolbox.AggregationStrategies;

/**
 * A Camel Java DSL Router
 */
public class HystrixRoute extends RouteBuilder {

    public void configure() {
        from("direct:start")
                .hystrix()
                .to("http://fooservice.com/slow")
                .onFallback()
                .transform().constant("Fallback message")
                .end()
                .to("mock:result");
    }




