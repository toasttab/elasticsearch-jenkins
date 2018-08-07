package com.speedledger.measure.jenkins;

import com.codahale.metrics.Metric;
import com.codahale.metrics.Timer;
import com.google.common.collect.Sets;
import hudson.EnvVars;
import hudson.Extension;
import hudson.Plugin;
import hudson.model.Job;
import hudson.model.Run;
import hudson.model.TaskListener;
import hudson.model.listeners.RunListener;
import hudson.model.queue.
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.JestResult;
import io.searchbox.client.config.ClientConfig;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Index;
import jenkins.model.Jenkins;
import jenkins.metrics.impl.JenkinsMetricProviderImpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Send Jenkins build information to Elasticsearch.
 */
@Extension
public class BuildListener extends RunListener<Run> {
    private static final Logger LOG = Logger.getLogger(BuildListener.class.getName());
    private final JestClient jestClient;
    private Config config;

    public BuildListener() {
        LOG.info("Initializing");

    /*    final JestClientFactory factory = new JestClientFactory();
        ClientConfig clientConfig = new ClientConfig.Builder(new ArrayList<String>()).multiThreaded(true).build();
        factory.setClientConfig(clientConfig);
        jestClient = factory.getObject();
    }*/
            final JestClientFactory factory = new JestClientFactory();
        HttpClientConfig clientConfig = new HttpClientConfig.Builder(new ArrayList<String>()).multiThreaded(true).build();
        factory.setHttpClientConfig(clientConfig);
        jestClient = factory.getObject();
    }

    public synchronized void loadConfig() {
        final Plugin plugin = Jenkins.getInstance().getPlugin("elasticsearch-jenkins");
        if (plugin instanceof ElasticsearchPlugin) {
            ElasticsearchPlugin elasticsearchPlugin = (ElasticsearchPlugin) plugin;

            Config newConfig = elasticsearchPlugin.getConfig();
            if (newConfig == null && config != null) {
                config = newConfig;
                LOG.info("Clearing configuration");
                jestClient.setServers(new HashSet<String>());
            } else if (newConfig != null && !newConfig.equals(config)) {
                config = newConfig;
                LOG.info("Applying new configuration: " + newConfig);
                jestClient.setServers(Sets.newHashSet(config.getUrl()));
            }
        } else {
            LOG.info("Could not find correct plugin");
        }
    }

    @Override
    public void onCompleted(Run run, TaskListener listener) {
        loadConfig();

        final boolean validConfig = config != null && config.nonEmptyValues();
        if (validConfig) {
            final Build build = createBuild(run, listener);

            try {
                final Index index = new Index.Builder(build).index(config.getIndexName()).type(config.getTypeName()).build();

                final JestResult result = jestClient.execute(index);

                if (result.isSucceeded()) {
                    LOG.fine("Sent build to Elasticsearch: " + build);
                } else {
                    LOG.warning("Failed to index build, got error message: " + result.getErrorMessage());
                }
            } catch (Exception e) {
                LOG.log(Level.SEVERE, "Error when sending build data: " + build, e);
            }
        } else {
            LOG.fine("The configuration is not valid, can not index the build");
        }
    }

    private Build createBuild(Run run, TaskListener listener) {
        JenkinsMetricProviderImpl metrics = new JenkinsMetricProviderImpl();
        Map<String, Metric> matricsMap = metrics.getMetricSet().getMetrics();
        Timer timer = new Timer();
        for(Map.Entry entry : matricsMap.entrySet()){
                System.out.println("Key: " + entry.getKey() + "\tValue: " + entry.getValue());
                if(entry.getKey().equals("jenkins.job.queuing.duration")){
                    System.out.println("******IN BLOCK ******");
                    timer = (Timer) entry.getValue();
                }
        }
        long time = timer.getCount();
        LOG.info("Time in queue:\t"+time);
        System.out.println("Time in queue:\t"+time);
        final Job job = run.getParent();
        HashMap<String, Long> stageTime =  new HashMap<String, Long>();
        stageTime.put("Build", 100L);
        stageTime.put("Farts", 200L);
        List<String> users = new ArrayList<String>();
        users.add("Jason");
        users.add("Doug");
        run.get
        final Build build = new Build();
        build.setDuration(run.getDuration());
        build.setJobName(job.getFullName());
        build.setResult(run.getResult().toString());
        build.setStartTime(run.getStartTimeInMillis());
        build.setNumber(run.getNumber());
        build.setTimestamp(run.getTimestamp());
        build.setCauseOfBuild(build.getCauseOfBuild());
        build.setSizeOfArtifacts(1000);
        build.setSizeOfWorkspace(100000);
        build.setTimeInQueue(123);
        build.setTimeInStages(build.getTimeInStages());
        build.setUsersWithChanges(build.getUsersWithChanges());

        return build;
    }
}
